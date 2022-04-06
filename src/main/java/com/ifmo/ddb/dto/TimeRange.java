package com.ifmo.ddb.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Builder
public class TimeRange {

    private LocalDate start;
    private LocalDate finish;

}
