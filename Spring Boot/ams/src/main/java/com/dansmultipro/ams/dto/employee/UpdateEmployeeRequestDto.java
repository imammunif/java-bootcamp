package com.dansmultipro.ams.dto.employee;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Date;

public class UpdateEmployeeRequestDto {

    @NotBlank(message = "Employee full name is required")
    @Size(max = 45, message = "Full name length exceeds limit")
    private String fullName;

    @NotBlank(message = "Employee address is required")
    @Size(max = 250, message = "Address length exceeds limit")
    private String address;

    //TODO: CHANGE TO STRING
    private Date dateOfBirth;

    @NotBlank(message = "Employee phone is required")
    @Size(max = 20, message = "Phone length exceeds limit")
    private String phone;

    @NotBlank(message = "Employee code is required")
    @Size(max = 20, message = "Code length exceeds limit")
    private String code;

    public String getFullName() {
        return fullName;
    }

    public String getAddress() {
        return address;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getPhone() {
        return phone;
    }

    public String getCode() {
        return code;
    }

}
