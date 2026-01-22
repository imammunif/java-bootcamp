package com.dansmultipro.tms.dto.productcustomer;

import java.util.UUID;

public class ProductCustomerResponseDto {

    private UUID id;
    private String customerName;
    private String productName;

    public ProductCustomerResponseDto(UUID id, String customerName, String productName) {
        this.id = id;
        this.customerName = customerName;
        this.productName = productName;
    }

    public UUID getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getProductName() {
        return productName;
    }

}
