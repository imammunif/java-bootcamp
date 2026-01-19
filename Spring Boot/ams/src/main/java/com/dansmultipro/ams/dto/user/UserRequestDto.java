package com.dansmultipro.ams.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserRequestDto {

    private String fullName;

    @NotBlank(message = "User role id is required")
    private String roleId;

    @NotBlank(message = "User employee id is required")
    private String employeeId;

    @Email(message = "Email format is not valid")
    @NotBlank(message = "User email is required")
    @Size(max = 50, message = "Email length exceeds limit")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(max = 200, message = "Password length exceeds limit")
    private String password;

    public String getRoleId() {
        return roleId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
