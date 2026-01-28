package com.dansmultipro.ims.dto.productcategory;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateProductCategoryRequestDto {

    @NotBlank(message = "Code is required")
    @Size(max = 10, message = "Code length exceeds limit, max 10 characters")
    private String code;

    @NotBlank(message = "Name is required")
    @Size(max = 40, message = "Name length exceeds limit, max 40 characters")
    private String name;

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

}
