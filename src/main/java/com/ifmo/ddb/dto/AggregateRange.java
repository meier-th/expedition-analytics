package com.ifmo.ddb.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@Getter
@Builder
public class AggregateRange {

    private Map<String, Aggregate> data = new HashMap<>();

}
