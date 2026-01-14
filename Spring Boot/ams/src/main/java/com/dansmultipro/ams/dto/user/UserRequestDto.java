package com.dansmultipro.ams.dto.user;

public class UserRequestDto {

    private String fullName;
    private String roleId;
    private String employeeId;
    private String email;
    private String password;

    public String getFullName() {
        return fullName;
    }

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

    public void setFullName(String fullName) {
        this.fullName = fullName;
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
