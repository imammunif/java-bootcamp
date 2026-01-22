package com.dansmultipro.tms.dto.productcustomer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UpdateProductCustomerRequestDto {

    @NotBlank(message = "Product is required")
    private String productId;

    @NotBlank(message = "Customer is required")
    private String customerId;

    @NotNull(message = "Version is required")
    private Integer version;

    public String getProductId() {
        return productId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public Integer getVersion() {
        return version;
    }

}
