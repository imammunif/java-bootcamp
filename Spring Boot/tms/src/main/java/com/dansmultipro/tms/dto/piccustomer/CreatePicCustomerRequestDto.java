package com.dansmultipro.tms.dto.piccustomer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public class CreatePicCustomerRequestDto {

    @NotBlank(message = "PIC is required")
    private String picId;

    @NotEmpty(message = "Customer(s) is required")
    private List<String> customerIdList;

    public String getPicId() {
        return picId;
    }

    public List<String> getCustomerIdList() {
        return customerIdList;
    }
}
