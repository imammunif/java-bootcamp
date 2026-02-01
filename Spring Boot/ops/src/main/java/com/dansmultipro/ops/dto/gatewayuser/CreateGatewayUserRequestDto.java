package com.dansmultipro.ops.dto.gatewayuser;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public class CreateGatewayUserRequestDto {

    @NotBlank(message = "Gateway is required")
    private String gatewayId;

    @NotEmpty(message = "User(s) is required")
    private List<String> userIdList;

    public String getGatewayId() {
        return gatewayId;
    }

    public List<String> getUserIdList() {
        return userIdList;
    }

}
