package com.ifmo.ddb.service.impl;

import com.ifmo.ddb.dto.Aggregate;
import com.ifmo.ddb.dto.AggregateRange;
import com.ifmo.ddb.dto.GroupBy;
import com.ifmo.ddb.dto.Query;
import com.ifmo.ddb.repo.CubeRepository;
import com.ifmo.ddb.service.AnalysisService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnalysisServiceImpl implements AnalysisService {

    private final CubeRepository repository;

    @Override
    public Aggregate getAggregateData(Query query) {
        return repository.findByQuery(query);
    }

    @Override
    public AggregateRange getAggregateRange(Query query, GroupBy groupBy) {
        return repository.findRangeByQuery(query, groupBy);
    }
}
