package com.ifmo.ddb.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class Query {

    private Set<Integer> ships;
    private Set<Integer> crews;
    private Set<Integer> routes;
    private TimeRange timeRange;

}
