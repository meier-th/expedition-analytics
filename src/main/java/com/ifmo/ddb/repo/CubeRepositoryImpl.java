package com.ifmo.ddb.repo;

import com.ifmo.ddb.dto.*;
import com.ifmo.ddb.entity.City;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
@Slf4j
@RequiredArgsConstructor
public class CubeRepositoryImpl implements CubeRepository {

    private final JdbcTemplate jdbcTemplate;

    private final static String QUERY_START = "SELECT AVG(profit) AS avg, SUM(profit) AS sum, COUNT(profit) AS count " +
            "FROM expedition_v WHERE ";
    private final static String AGG_QUERY = "SELECT %s as %s, AVG(profit) AS avg, SUM(profit) AS sum, COUNT(profit) " +
            "AS count FROM expedition_v WHERE ";
    private final static String JOINED_WITH_INTERVALS_QUERY = "SELECT interval_v.year AS year, AVG(profit) AS avg, " +
            "SUM(profit) AS sum, COUNT(profit) AS count FROM expedition_v INNER JOIN interval_v ON " +
            "expedition_v.interval_id = interval_v.id WHERE ";
    private final static String GET_FIELD_VALUES_QUERY = "SELECT DISTINCT %s AS field FROM expedition_v;";
    private final static String GET_GENERAL_TIME_RANGE_QUERY =
            "SELECT MIN(start_date) AS start, MAX(finish_date) AS finish FROM expedition_v;";
    private final static String ROUTES_QUERY = "SELECT city_from.id AS fromId, city_from.name AS fromName, " +
            "city_from.latitude AS fromLatitude, city_from.longitude AS fromLongitude, city_to.id AS toId, " +
            "city_to.name AS toName, city_to.latitude AS toLatitude, city_to.longitude AS toLongitude FROM " +
            "route INNER JOIN city city_from ON route.city_from = city_from.id INNER JOIN city city_to ON " +
            "route.city_to = city_to.id WHERE route.route_id IN (";

    @Override
    public Aggregate findByQuery(Query query) {
        String queryStr = buildQuery(QUERY_START, query);
        log.info(queryStr);
        return jdbcTemplate.query(queryStr, (rs, rowNum) -> {
            int sum = rs.getInt("sum");
            int count = rs.getInt("count");
            double average = rs.getDouble("avg");
            return Aggregate.builder()
                    .avg(average)
                    .sum(sum)
                    .count(count)
                    .build();
        })
                .get(0);
    }

    @Override
    public AggregateRange findRangeByQuery(Query query, GroupBy groupBy) {
        String queryStr;

        if (groupBy == GroupBy.year) {
            queryStr = buildQuery(JOINED_WITH_INTERVALS_QUERY, query);
        } else {
            queryStr = buildQuery(String.format(AGG_QUERY, groupBy.name(), groupBy.name()), query);
        }
        queryStr += buildGroupByCause(groupBy);

        log.info(queryStr);
        List<GroupedAggregate> groupedAggregates = jdbcTemplate.query(queryStr, (rs, rowNum) -> {
            int sum = rs.getInt("sum");
            int count = rs.getInt("count");
            double average = rs.getDouble("avg");
            int year = rs.getInt(groupBy.name());
            Aggregate aggregate = Aggregate.builder()
                    .avg(average)
                    .sum(sum)
                    .count(count).build();
            return new GroupedAggregate(year, aggregate);
        });

        return new AggregateRange(groupedAggregates);
    }

    @Override
    public Set<Integer> getDistinctValuesForField(GroupBy field) {
        if (field == GroupBy.year) {
            return Collections.emptySet();
        }
        return new HashSet<>(jdbcTemplate.query(String.format(GET_FIELD_VALUES_QUERY, field.name()),
                (rs, rowNum) -> rs.getInt("field")));
    }

    @Override
    public TimeRange getGeneralTimeRange() {
        return jdbcTemplate.query(GET_GENERAL_TIME_RANGE_QUERY, (rs, rowNum) -> {
            LocalDate start = LocalDate.parse(rs.getString("start"));
            LocalDate finish = LocalDate.parse(rs.getString("finish"));
            return new TimeRange(start, finish);
        }).get(0);
    }

    @Override
    public List<Route> loadRoutes(Set<Integer> routeIds) {
        StringBuilder query = new StringBuilder(ROUTES_QUERY);
        routeIds.forEach(id -> query.append(id).append(","));
        query.delete(query.length()-1, query.length());
        query.append(')');
        return jdbcTemplate.query(query.toString(), (rs, rowNum) -> {
            int fromId = rs.getInt("fromId");
            String fromName = rs.getString("fromName");
            double fromLatitude = rs.getDouble("fromLatitude");
            double fromLongitude = rs.getDouble("fromLongitude");
            City cityFrom = new City(fromId, fromName, fromLatitude, fromLongitude);

            int toId = rs.getInt("toId");
            String toName = rs.getString("toName");
            double toLatitude = rs.getDouble("toLatitude");
            double toLongitude = rs.getDouble("toLongitude");
            City cityTo = new City(toId, toName, toLatitude, toLongitude);
            return new Route(cityFrom, cityTo);
        });
    }

    private String buildQuery(String queryStart, Query query) {
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append(queryStart);
        boolean added = false;
        if (!query.getCrews().isEmpty()) {
            queryBuilder.append("crew IN ").append(buildInClause(query.getCrews())).append(" AND ");
            added = true;
        }
        if (!query.getRoutes().isEmpty()) {
            queryBuilder.append("route IN ").append(buildInClause(query.getRoutes())).append(" AND ");
            added = true;
        }
        if (!query.getShips().isEmpty()) {
            queryBuilder.append("ship IN ").append(buildInClause(query.getShips())).append(" AND ");
            added = true;
        }
        if (query.getTimeRange() != null) {
            queryBuilder.append("start_date BETWEEN \"").append(query.getTimeRange().getStart()).append("\" AND \"")
                    .append(query.getTimeRange().getFinish()).append("\" AND ");
            added = true;
        }
        if (added) {
            queryBuilder.delete(queryBuilder.length() - 5, queryBuilder.length());
        } else {
            queryBuilder.delete(queryBuilder.length() - 7, queryBuilder.length());
        }
        return queryBuilder.toString();
    }

    private String buildGroupByCause(GroupBy groupBy) {
        return " GROUP BY " + groupBy.name();
    }

    private String buildInClause(Set<Integer> values) {
        StringBuilder builder = new StringBuilder();
        builder.append('(');
        values.forEach(value -> builder.append(value).append(", "));
        builder.delete(builder.length() - 2, builder.length());
        builder.append(')');
        return builder.toString();
    }

}
