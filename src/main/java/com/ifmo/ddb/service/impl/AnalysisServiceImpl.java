package com.ifmo.ddb.service.impl;

import com.ifmo.ddb.dto.*;
import com.ifmo.ddb.entity.repo.CityRepository;
import com.ifmo.ddb.entity.repo.CrewRepository;
import com.ifmo.ddb.entity.repo.ShipRepository;
import com.ifmo.ddb.repo.CubeRepository;
import com.ifmo.ddb.service.AnalysisService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnalysisServiceImpl implements AnalysisService {

    private final CubeRepository cubeRepository;
    private final CrewRepository crewRepository;
    private final ShipRepository shipRepository;

    @Override
    public Aggregate getAggregateData(Query query) {
        return cubeRepository.findByQuery(query);
    }

    @Override
    public AggregateRange getAggregateRange(Query query, GroupBy groupBy) {
        return cubeRepository.findRangeByQuery(query, groupBy);
    }

    @Override
    public Options getQueryOptions() {
        Options options = new Options();
        options.setCrews(crewRepository.findAllById(cubeRepository.getDistinctValuesForField(GroupBy.crew)));
        options.setRoutes(cubeRepository.loadRoutes(cubeRepository.getDistinctValuesForField(GroupBy.route)));
        options.setShips(shipRepository.findAllById(cubeRepository.getDistinctValuesForField(GroupBy.ship)));
        options.setTimeRange(cubeRepository.getGeneralTimeRange());
        return options;
    }

    

}
