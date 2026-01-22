package com.dansmultipro.tms.dto.productcustomer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public class CreateProductCustomerRequestDto {

    @NotBlank(message = "Customer is required")
    private String customerId;

    @NotEmpty(message = "Product(s) is required")
    private List<String> productIdList;

    public String getCustomerId() {
        return customerId;
    }

    public List<String> getProductIdList() {
        return productIdList;
    }

}
