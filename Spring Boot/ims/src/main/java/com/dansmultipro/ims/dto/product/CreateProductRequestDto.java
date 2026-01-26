package com.dansmultipro.ims.dto.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateProductRequestDto {

    @NotBlank(message = "Name is required")
    @Size(max = 40, message = "Name length exceeds limit, max 40 characters")
    private String name;

    @NotBlank(message = "Product category is required")
    private String categoryId;

    public String getName() {
        return name;
    }

    public String getCategoryId() {
        return categoryId;
    }

}
