package com.dansmultipro.tms.dto.piccustomer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class UpdatePicCustomerRequestDto {

    @NotBlank(message = "PIC is required")
    private String picId;

    @NotEmpty(message = "Customer(s) is required")
    private List<String> customerIdList;

    @NotNull(message = "Version is required")
    private Integer version;

    public String getPicId() {
        return picId;
    }

    public List<String> getCustomerIdList() {
        return customerIdList;
    }

    public Integer getVersion() {
        return version;
    }

}
