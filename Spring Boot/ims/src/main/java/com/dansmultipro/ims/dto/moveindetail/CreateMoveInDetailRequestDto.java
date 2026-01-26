package com.dansmultipro.ims.dto.moveindetail;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateMoveInDetailRequestDto {

    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Product quantity must be at least 1")
    @Max(value = 100, message = "Product quantity cannot exceed 100")
    private Integer quantity;

    @NotBlank(message = "Product is required")
    private String productId;

    @NotBlank(message = "Move in supply is required")
    private String moveInId;

    public Integer getQuantity() {
        return quantity;
    }

    public String getProductId() {
        return productId;
    }

    public String getMoveInId() {
        return moveInId;
    }

}
