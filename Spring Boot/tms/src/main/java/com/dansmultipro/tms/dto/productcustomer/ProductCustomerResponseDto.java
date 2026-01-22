package com.dansmultipro.tms.dto.productcustomer;

import java.util.UUID;

public class ProductCustomerResponseDto {

    private UUID id;
    private String productName;
    private String customerName;

    public ProductCustomerResponseDto(UUID id, String productName, String customerName) {
        this.id = id;
        this.productName = productName;
        this.customerName = customerName;
    }

    public UUID getId() {
        return id;
    }

    public String getFullName() {
        return productName;
    }

    public String getRoleName() {
        return customerName;
    }

}
