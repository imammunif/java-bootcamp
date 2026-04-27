package com.dansmultipro.ops.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponseDto<T> {

    private final T message;

}
