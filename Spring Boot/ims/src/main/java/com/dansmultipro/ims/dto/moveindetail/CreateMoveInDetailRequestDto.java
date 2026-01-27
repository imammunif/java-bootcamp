package com.dansmultipro.ims.dto.moveindetail;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateMoveInDetailRequestDto {

    @NotBlank(message = "Product is required")
    private String productId;

    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Product quantity must be at least 1")
    private Integer quantity;

    public Integer getQuantity() {
        return quantity;
    }

    public String getProductId() {
        return productId;
    }

}
