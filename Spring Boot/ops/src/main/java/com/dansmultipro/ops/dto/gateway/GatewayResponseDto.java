package com.dansmultipro.ops.dto.gateway;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class GatewayResponseDto {

    private UUID id;
    private String name;
    private String code;

}
