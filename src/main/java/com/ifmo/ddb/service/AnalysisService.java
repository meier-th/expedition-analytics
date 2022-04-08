package com.ifmo.ddb.service;

import com.ifmo.ddb.dto.*;

public interface AnalysisService {

    Aggregate getAggregateData(Query query);

    AggregateRange getAggregateRange(Query query, GroupBy groupBy);

    Options getQueryOptions();

}
