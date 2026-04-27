package com.dansmultipro.ops.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class CreateResponseDto {

    private UUID id;
    private String message;
}
