package com.dansmultipro.ams.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UpdateUserRequestDto {

    @Email(message = "Email format is not valid")
    @NotBlank(message = "User email is required")
    @Size(max = 50, message = "Email length exceeds limit, max 50 characters")
    private String email;

    @NotNull(message = "Version is required")
    private Integer version;

    public String getEmail() {
        return email;
    }

    public Integer getVersion() {
        return version;
    }

}
