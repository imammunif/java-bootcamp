package com.dansmultipro.ims.dto.agent;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UpdateAgentRequestDto {

    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name length exceeds limit, max 100 characters")
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
