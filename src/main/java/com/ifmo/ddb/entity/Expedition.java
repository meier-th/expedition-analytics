package com.ifmo.ddb.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
@Setter
@AllArgsConstructor
public class Expedition {

    @Id
    @GeneratedValue
    private int id;
    private City startCity;
    private City finishCity;
    private Ship ship;
    private Crew crew;
    private Interval interval;
    private int profit;


}
