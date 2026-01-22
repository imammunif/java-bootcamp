package com.dansmultipro.tms.dto.message;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UpdateMessageRequestDto {

    @NotBlank(message = "Message is required")
    @Size(max = 300, message = "Message length exceeds limit, max 300 characters")
    private String description;

    @NotNull(message = "Version is required")
    private Integer version;

    public String getDescription() {
        return description;
    }

    public Integer getVersion() {
        return version;
    }

}
