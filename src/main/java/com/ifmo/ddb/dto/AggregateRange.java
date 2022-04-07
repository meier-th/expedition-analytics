package com.ifmo.ddb.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class AggregateRange {

    private List<GroupedAggregate> data;

}
