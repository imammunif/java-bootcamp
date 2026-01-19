package com.dansmultipro.ams.dto.location;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UpdateLocationRequestDto {

    @NotBlank(message = "Location name is required")
    @Size(max = 35, message = "Name length exceeds limit")
    private String name;

    public UpdateLocationRequestDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
