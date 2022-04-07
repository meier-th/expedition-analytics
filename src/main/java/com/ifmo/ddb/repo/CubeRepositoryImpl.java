package com.ifmo.ddb.repo;

import com.ifmo.ddb.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CubeRepositoryImpl implements CubeRepository {

    private final JdbcTemplate jdbcTemplate;

    private final static String QUERY_START = "SELECT AVG(profit), SUM(profit), COUNT(profit) FROM expedition_v WHERE ";
    private final static String AGG_QUERY = "SELECT %s as %s, AVG(profit), SUM(profit), COUNT(profit) " +
            "FROM expedition_v WHERE ";
    private final static String JOINED_WITH_INTERVALS_QUERY = "SELECT interval_v.year AS year, AVG(profit), " +
            "SUM(profit), COUNT(profit) FROM expedition_v INNER JOIN interval_v ON " +
            "expedition_v.start_date = interval_v.id WHERE ";

    @Override
    public Aggregate findByQuery(Query query) {
        String queryStr = buildQuery(QUERY_START, query);
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
            queryStr += buildGroupByCause(groupBy);
        }

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

    private String buildQuery(String queryStart, Query query) {
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append(queryStart);
        if (!query.getCrews().isEmpty()) {
            queryBuilder.append("crew IN ").append(buildInClause(query.getCrews())).append(" AND ");
        }
        if (!query.getRoutes().isEmpty()) {
            queryBuilder.append("route IN ").append(buildInClause(query.getRoutes())).append(" AND ");
        }
        if (!query.getShips().isEmpty()) {
            queryBuilder.append("ship IN ").append(buildInClause(query.getShips())).append(" AND ");
        }
        if (query.getTimeRange() != null) {
            queryBuilder.append("interval BETWEEN ").append(query.getTimeRange().getStart()).append(" AND ")
                    .append(query.getTimeRange().getFinish()).append(" AND ");
        }
        queryBuilder.delete(queryBuilder.length() - 5, queryBuilder.length());
        return queryBuilder.toString();
    }

    private String buildGroupByCause(GroupBy groupBy) {
        return " GROUP BY " + groupBy.name();
    }

    private String buildInClause(List<Integer> values) {
        StringBuilder builder = new StringBuilder();
        builder.append('(');
        values.forEach(value -> builder.append(value).append(", "));
        builder.append(')');
        return builder.toString();
    }

}
