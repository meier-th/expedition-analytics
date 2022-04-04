package com.ifmo.ddb.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Builder
public class TimeRange {

    private long start;
    private long finish;

}