package com.ifmo.ddb.dto;

import com.ifmo.ddb.entity.City;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class Route {

    int id;
    private City from;
    private City to;

}
