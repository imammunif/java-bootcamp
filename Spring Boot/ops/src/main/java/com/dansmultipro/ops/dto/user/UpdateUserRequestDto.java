package com.dansmultipro.ops.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserRequestDto {

    @NotBlank(message = "Name is required")
    @Size(max = 50, message = "Name length exceeds limit, max 50 characters")
    private String name;

    @Email(message = "Email format is not valid")
    @NotBlank(message = "Email is required")
    @Size(max = 50, message = "Email length exceeds limit, max 50 characters")
    private String email;

    @NotNull(message = "Version is required")
    private Integer version;

}
