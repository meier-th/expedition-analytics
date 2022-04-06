package com.ifmo.ddb.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Builder
public class Aggregate {

    private int sum;
    private double avg;
    private int count;

}
