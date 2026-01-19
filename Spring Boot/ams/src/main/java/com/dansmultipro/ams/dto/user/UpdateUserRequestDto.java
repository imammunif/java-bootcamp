package com.dansmultipro.ams.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UpdateUserRequestDto {

    @Email(message = "Email format is not valid")
    @NotBlank(message = "user email is required")
    @Size(max = 50, message = "Email length exceeds limit")
    private String email;

    public String getEmail() {
        return email;
    }

}
