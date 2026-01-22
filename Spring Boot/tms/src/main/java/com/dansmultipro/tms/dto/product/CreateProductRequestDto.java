package com.dansmultipro.tms.dto.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateProductRequestDto {

    @NotBlank(message = "Product name is required")
    @Size(max = 35, message = "Name length exceeds limit, max 35 characters")
    private String name;

    public String getName() {
        return name;
    }

}
