package com.ifmo.ddb.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Setter
@AllArgsConstructor
public class Expedition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "start_city")
    private City startCity;
    @ManyToOne
    @JoinColumn(name = "finish_city")
    private City finishCity;
    @ManyToOne
    @JoinColumn(name = "ship")
    private Ship ship;
    @ManyToOne
    @JoinColumn(name = "crew")
    private Crew crew;
    @OneToOne
    @JoinColumn(name = "interval_r")
    private Interval interval;
    private int profit;


}
