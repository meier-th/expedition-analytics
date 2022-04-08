package com.ifmo.ddb.dto;

import com.ifmo.ddb.entity.Crew;
import com.ifmo.ddb.entity.Ship;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class Options {

    private List<Ship> ships;
    private List<Crew> crews;
    private List<Route> routes;
    private TimeRange timeRange;

}
