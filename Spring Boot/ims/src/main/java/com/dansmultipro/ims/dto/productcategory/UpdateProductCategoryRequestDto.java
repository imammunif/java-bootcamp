package com.dansmultipro.ims.dto.productcategory;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UpdateProductCategoryRequestDto {

    @NotBlank(message = "Code is required")
    @Size(max = 10, message = "Code length exceeds limit, max 10 characters")
    private String code;

    @NotBlank(message = "Name is required")
    @Size(max = 40, message = "Name length exceeds limit, max 40 characters")
    private String name;

    @NotNull(message = "Version is required")
    private Integer version;

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public Integer getVersion() {
        return version;
    }

}
