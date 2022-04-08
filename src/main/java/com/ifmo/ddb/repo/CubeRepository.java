package com.ifmo.ddb.repo;

import com.ifmo.ddb.dto.*;

import java.util.List;
import java.util.Set;

public interface CubeRepository {

    Aggregate findByQuery(Query query);

    AggregateRange findRangeByQuery(Query query, GroupBy groupBy);

    Set<Integer> getDistinctValuesForField(GroupBy field);

    TimeRange getGeneralTimeRange();

    List<Route> loadRoutes(Set<Integer> routeIds);

}
