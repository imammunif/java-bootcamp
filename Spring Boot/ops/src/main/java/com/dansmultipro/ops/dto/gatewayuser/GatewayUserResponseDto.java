package com.dansmultipro.ops.dto.gatewayuser;

import java.util.UUID;

public class GatewayUserResponseDto {

    private UUID id;
    private String gatewayName;
    private String customerName;

    public GatewayUserResponseDto(UUID id, String gatewayName, String customerName) {
        this.id = id;
        this.gatewayName = gatewayName;
        this.customerName = customerName;
    }

    public UUID getId() {
        return id;
    }

    public String getGatewayName() {
        return gatewayName;
    }

    public String getCustomerName() {
        return customerName;
    }

}
