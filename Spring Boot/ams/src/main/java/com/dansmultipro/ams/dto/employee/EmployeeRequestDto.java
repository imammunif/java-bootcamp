package com.dansmultipro.ams.dto.employee;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Date;

public class EmployeeRequestDto {

    @NotBlank(message = "Employee full name is required")
    @Size(max = 45, message = "Full name length exceeds limit")
    private String fullName;

    @NotBlank(message = "Employee company id is required")
    private String companyId;

    @NotBlank(message = "Employee code is required")
    private String code;

    @NotBlank(message = "Employee phone is required")
    private String phone;

    @NotBlank(message = "Employee address is required")
    @Size(max = 250, message = "Address length exceeds limit")
    private String address;

    //TODO: CHANGE TO STRING
    private Date dateOfBirth;

    public String getFullName() {
        return fullName;
    }

    public String getCompanyId() {
        return companyId;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getCode() {
        return code;
    }

}
