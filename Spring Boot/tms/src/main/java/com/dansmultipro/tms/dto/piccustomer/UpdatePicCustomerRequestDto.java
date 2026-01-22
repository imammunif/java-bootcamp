package com.dansmultipro.tms.dto.piccustomer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UpdatePicCustomerRequestDto {

    @NotBlank(message = "PIC is required")
    private String picId;

    @NotBlank(message = "Customer is required")
    private String customerId;

    @NotNull(message = "Version is required")
    private Integer version;

    public Integer getVersion() {
        return version;
    }

    public String getPicId() {
        return picId;
    }

    public String getCustomerId() {
        return customerId;
    }

}
