package com.dansmultipro.ops.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PaginatedResponseDto<T> {

    private List<T> data;
    private Long total;

}
