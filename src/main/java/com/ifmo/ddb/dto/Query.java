package com.ifmo.ddb.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
@Builder
public class Query {

    private List<Integer> ships;
    private List<Integer> crews;
    private List<Integer> routes;
    private TimeRange timeRange;

}
