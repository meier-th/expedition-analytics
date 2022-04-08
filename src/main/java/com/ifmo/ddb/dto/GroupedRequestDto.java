package com.ifmo.ddb.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class GroupedRequestDto {

    private Query query;
    private GroupBy groupBy;

}
