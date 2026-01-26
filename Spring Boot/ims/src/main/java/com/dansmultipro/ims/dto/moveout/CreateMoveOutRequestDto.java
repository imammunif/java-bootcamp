package com.dansmultipro.ims.dto.moveout;

import jakarta.validation.constraints.NotBlank;

public class CreateMoveOutRequestDto {

    @NotBlank(message = "Product is required")
    private String productId;

    @NotBlank(message = "Agent is required")
    private String agentId;

    public String getProductId() {
        return productId;
    }

    public String getAgentId() {
        return agentId;
    }

}
