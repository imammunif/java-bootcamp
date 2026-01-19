package com.dansmultipro.ams.dto.company;

import jakarta.validation.constraints.NotBlank;

public class UpdateCompanyRequestDto {

    @NotBlank(message = "Company name is required")
    private String name;

    @NotBlank(message = "Version is required")
    private String version;

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

}
