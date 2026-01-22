package com.dansmultipro.tms.dto.company;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateCompanyRequestDto {

    @NotBlank(message = "Company name is required")
    @Size(max = 20, message = "Name length exceeds limit, max 20 characters")
    private String name;

    public String getName() {
        return name;
    }

}
