package com.dansmultipro.ams.dto.location;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UpdateLocationRequestDto {

    @NotBlank(message = "Location name is required")
    @Size(max = 35, message = "Name length exceeds limit, max 35 characters")
    private String name;

    @NotNull(message = "Version is required")
    private Integer version;

    public UpdateLocationRequestDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer getVersion() {
        return version;
    }

}
