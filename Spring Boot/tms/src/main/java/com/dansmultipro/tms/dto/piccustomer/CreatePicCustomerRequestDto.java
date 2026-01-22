package com.dansmultipro.tms.dto.piccustomer;

import jakarta.validation.constraints.NotBlank;

public class CreatePicCustomerRequestDto {

    @NotBlank(message = "PIC is required")
    private String picId;

    @NotBlank(message = "Customer is required")
    private String customerId;

    public String getPicId() {
        return picId;
    }

    public void setPicId(String picId) {
        this.picId = picId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

}
