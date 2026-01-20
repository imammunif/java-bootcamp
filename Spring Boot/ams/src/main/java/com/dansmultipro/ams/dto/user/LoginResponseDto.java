package com.dansmultipro.ams.dto.user;

public class LoginResponseDto {

    private String fullName;
    private String roleCode;
    private String token;

    public LoginResponseDto(String fullName, String roleCode, String token) {
        this.fullName = fullName;
        this.roleCode = roleCode;
        this.token = token;
    }

    public String getFullName() {
        return fullName;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public String getToken() {
        return token;
    }

}
