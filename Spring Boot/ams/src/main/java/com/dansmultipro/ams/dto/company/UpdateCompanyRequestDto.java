package com.dansmultipro.ams.dto.company;

import jakarta.validation.constraints.NotBlank;

public class UpdateCompanyRequestDto {

    @NotBlank(message = "Company name is required")
    private String name;

    public String getName() {
        return name;
    }

}
