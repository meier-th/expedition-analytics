package com.ifmo.ddb.service;

import com.ifmo.ddb.dto.Aggregate;
import com.ifmo.ddb.dto.AggregateRange;
import com.ifmo.ddb.dto.GroupBy;
import com.ifmo.ddb.dto.Query;

public interface AnalysisService {

    Aggregate getAggregateData(Query query);

    AggregateRange getAggregateRange(Query query, GroupBy groupBy);

}
