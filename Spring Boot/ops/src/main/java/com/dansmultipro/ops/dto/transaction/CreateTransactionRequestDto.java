package com.dansmultipro.ops.dto.transaction;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigInteger;

public class CreateTransactionRequestDto {

    @NotNull(message = "Virtual account number is required")
    @Min(value = 0, message = "Virtual account cannot be negative")
    private Integer virtualNumber;

    @NotNull(message = "Transaction amount is required")
    @Min(value = 5000, message = "Amount minimum at least 5000")
    private BigInteger amount;

    @NotBlank(message = "Gateway is required")
    private String gatewayId;

    @NotBlank(message = "Product is required")
    private String productId;

    public Integer getVirtualNumber() {
        return virtualNumber;
    }

    public BigInteger getAmount() {
        return amount;
    }

    public String getGatewayId() {
        return gatewayId;
    }

    public String getProductId() {
        return productId;
    }

}
