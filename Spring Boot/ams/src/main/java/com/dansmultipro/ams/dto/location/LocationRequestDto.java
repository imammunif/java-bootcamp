package com.dansmultipro.ams.dto.location;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LocationRequestDto {

    @NotBlank(message = "Location name is required")
    @Size(max = 35, message = "Location length exceeds limit, max 35 characters")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
