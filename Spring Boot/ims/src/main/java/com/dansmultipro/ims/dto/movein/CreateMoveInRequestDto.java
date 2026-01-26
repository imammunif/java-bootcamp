package com.dansmultipro.ims.dto.movein;

import jakarta.validation.constraints.NotBlank;

public class CreateMoveInRequestDto {

    @NotBlank(message = "Product is required")
    private String productId;

    @NotBlank(message = "Supplier is required")
    private String supplierId;

    public String getProductId() {
        return productId;
    }

    public String getSupplierId() {
        return supplierId;
    }

}
