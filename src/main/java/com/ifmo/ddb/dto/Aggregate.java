package com.ifmo.ddb.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Setter
public class Aggregate {

    private int sum;
    private double avg;
    private int count;

}
