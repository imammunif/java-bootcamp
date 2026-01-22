package com.dansmultipro.tms.dto.productcustomer;

import jakarta.validation.constraints.NotBlank;

public class CreateProductCustomerRequestDto {

    @NotBlank(message = "Customer is required")
    private String customerId;

    @NotBlank(message = "Product is required")
    private String productId;

    public String getCustomerId() {
        return customerId;
    }

    public String getProductId() {
        return productId;
    }

}
