package com.dansmultipro.ops.dto.login;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponseDto {

    private String fullName;
    private String roleCode;
    private String token;

}
