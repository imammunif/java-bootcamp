package com.dansmultipro.ams.dto.company;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UpdateCompanyRequestDto {

    @NotBlank(message = "Company name is required")
    @Size(max = 35, message = "Name length exceeds limit, max 35 characters")
    private String name;

    @NotNull(message = "Version is required")
    private Integer version;

    public String getName() {
        return name;
    }

    public Integer getVersion() {
        return version;
    }

}
