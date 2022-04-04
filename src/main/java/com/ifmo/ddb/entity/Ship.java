package com.ifmo.ddb.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Getter
@Builder
public class Ship {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    private int speed;
    private int fuelConsumption;

}
