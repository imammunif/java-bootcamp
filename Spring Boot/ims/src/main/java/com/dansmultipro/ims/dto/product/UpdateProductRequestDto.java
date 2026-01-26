package com.dansmultipro.ims.dto.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UpdateProductRequestDto {

    @NotBlank(message = "Product name is required")
    @Size(max = 40, message = "Name length exceeds limit, max 40 characters")
    private String name;

    @NotBlank(message = "Product category is required")
    private String categoryId;

    @NotNull(message = "Version is required")
    private Integer version;

    public String getName() {
        return name;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public Integer getVersion() {
        return version;
    }

}
