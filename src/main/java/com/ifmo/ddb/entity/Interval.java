package com.ifmo.ddb.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Getter
@Builder
public class Interval {

    @Id
    @GeneratedValue
    private int id;
    private LocalDate startDate;
    private LocalDate finishDate;

}
