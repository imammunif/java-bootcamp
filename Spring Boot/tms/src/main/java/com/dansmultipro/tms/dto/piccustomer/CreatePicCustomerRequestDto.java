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

    public String getCustomerId() {
        return customerId;
    }

}
