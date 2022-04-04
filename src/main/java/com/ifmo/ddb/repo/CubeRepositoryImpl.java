package com.ifmo.ddb.repo;

import com.ifmo.ddb.dto.Aggregate;
import com.ifmo.ddb.dto.AggregateRange;
import com.ifmo.ddb.dto.GroupBy;
import com.ifmo.ddb.dto.Query;
import org.springframework.stereotype.Repository;

@Repository
public class CubeRepositoryImpl implements CubeRepository {

    @Override
    public Aggregate findByQuery(Query query) {
        return null;
    }

    @Override
    public AggregateRange findRangeByQuery(Query query, GroupBy groupBy) {
        return null;
    }
}
