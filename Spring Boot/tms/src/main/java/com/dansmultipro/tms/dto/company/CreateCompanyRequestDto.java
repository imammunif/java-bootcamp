package com.dansmultipro.tms.dto.company;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateCompanyRequestDto {

    @NotBlank(message = "Company name is required")
    @Size(max = 35, message = "Name length exceeds limit, max 35 characters")
    private String name;

    public String getName() {
        return name;
    }

}
