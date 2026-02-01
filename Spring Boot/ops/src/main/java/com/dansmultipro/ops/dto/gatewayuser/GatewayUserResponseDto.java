package com.dansmultipro.ops.dto.gatewayuser;

import java.util.UUID;

public class GatewayUserResponseDto {

    private UUID id;
    private String userName;
    private String email;
    private String gatewayName;

    public GatewayUserResponseDto(UUID id, String userName, String email, String gatewayName) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.gatewayName = gatewayName;
    }

    public UUID getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getGatewayName() {
        return gatewayName;
    }

}
