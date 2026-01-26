package com.dansmultipro.ims.dto.agent;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateAgentRequestDto {

    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name length exceeds limit, max 100 characters")
    private String name;

    public String getName() {
        return name;
    }

}
