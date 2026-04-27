package com.dansmultipro.ops.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class UserGatewayResponseDto {

    private UUID id;
    private String name;
    private String email;
    private String roleName;
    private String gatewayName;
    private String version;

}
