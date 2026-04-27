package com.dansmultipro.ops.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateResponseDto {

    private final Integer version;
    private final String message;

}
