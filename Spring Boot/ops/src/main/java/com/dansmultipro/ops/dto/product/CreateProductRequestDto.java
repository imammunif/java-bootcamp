package com.dansmultipro.ops.dto.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProductRequestDto {

    @NotBlank(message = "Name is required")
    @Size(max = 40, message = "Name length exceeds limit, max 40 characters")
    private String name;

    @NotBlank(message = "Code is required")
    @Size(max = 5, message = "Code length exceeds limit, max 5 characters")
    private String code;

}
