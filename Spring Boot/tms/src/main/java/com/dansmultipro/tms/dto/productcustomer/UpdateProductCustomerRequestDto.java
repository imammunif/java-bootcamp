package com.dansmultipro.tms.dto.productcustomer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class UpdateProductCustomerRequestDto {

    @NotBlank(message = "Customer is required")
    private String customerId;

    @NotEmpty(message = "Product(s) is required")
    private List<String> productIdList;

    @NotNull(message = "Version is required")
    private Integer version;

    public String getCustomerId() {
        return customerId;
    }

    public List<String> getProductIdList() {
        return productIdList;
    }

    public Integer getVersion() {
        return version;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setProductIdList(List<String> productIdList) {
        this.productIdList = productIdList;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

}
