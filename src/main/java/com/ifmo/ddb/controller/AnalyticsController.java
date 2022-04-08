package com.ifmo.ddb.controller;

import com.ifmo.ddb.dto.*;
import com.ifmo.ddb.service.AnalysisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/analytics")
@RequiredArgsConstructor
@Slf4j
public class AnalyticsController {

    private final AnalysisService analysisService;

    @PostMapping("/full")
    public Aggregate getFullData(@RequestBody Query query) {
        return analysisService.getAggregateData(query);
    }

    @PostMapping("/grouped")
    public AggregateRange getRangedData(@RequestBody GroupedRequestDto requestDto) {
        return analysisService.getAggregateRange(requestDto.getQuery(), requestDto.getGroupBy());
    }

    @GetMapping("/options")
    public Options getQueryOptions() {
        return analysisService.getQueryOptions();
    }

    @ExceptionHandler(Exception.class)
    void handleException(Exception error) {
        log.warn(error.getMessage());
    }

}
