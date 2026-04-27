package com.dansmultipro.ops.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangePasswordRequestDto {

    @NotBlank(message = "Old password is required")
    @Size(min = 8, message = "Password must be at least 8 characters")
    @Size(max = 200, message = "Password length exceeds limit, max 200 characters")
    private String oldPassword;

    @NotBlank(message = "New password is required")
    @Size(min = 8, message = "Password must be at least 8 characters")
    @Size(max = 200, message = "Password length exceeds limit, max 200 characters")
    private String newPassword;

}
