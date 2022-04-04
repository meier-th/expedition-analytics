package com.ifmo.ddb.repo;

import com.ifmo.ddb.dto.Aggregate;
import com.ifmo.ddb.dto.AggregateRange;
import com.ifmo.ddb.dto.GroupBy;
import com.ifmo.ddb.dto.Query;

public interface CubeRepository {

    Aggregate findByQuery(Query query);

    AggregateRange findRangeByQuery(Query query, GroupBy groupBy);

}
