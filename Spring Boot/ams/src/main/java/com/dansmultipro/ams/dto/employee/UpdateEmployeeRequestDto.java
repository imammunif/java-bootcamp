package com.dansmultipro.ams.dto.employee;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UpdateEmployeeRequestDto {

    @NotBlank(message = "Employee full name is required")
    @Size(max = 45, message = "Full name length exceeds limit, max 45 characters")
    private String fullName;

    @NotBlank(message = "Employee address is required")
    @Size(max = 250, message = "Address length exceeds limit, max 250 characters")
    private String address;

    @NotBlank(message = "Employee date of birth is required")
    @Size(max = 10, message = "Date of birth length exceeds limit, max 10 characters")
    private String dateOfBirth;

    @NotBlank(message = "Employee phone is required")
    @Size(max = 20, message = "Phone length exceeds limit, max 20 characters")
    private String phone;

    @NotBlank(message = "Employee code is required")
    @Size(max = 20, message = "Code length exceeds limit, max 20 characters")
    private String code;

    @NotNull(message = "Version is required")
    private Integer version;

    public String getFullName() {
        return fullName;
    }

    public String getAddress() {
        return address;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getPhone() {
        return phone;
    }

    public String getCode() {
        return code;
    }

    public Integer getVersion() {
        return version;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

}
