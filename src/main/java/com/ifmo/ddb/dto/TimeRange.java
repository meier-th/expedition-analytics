package com.ifmo.ddb.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
public class TimeRange {

    private LocalDate start;
    private LocalDate finish;

}
