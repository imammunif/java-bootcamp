package com.dansmultipro.ams.dto.employee;

import java.util.Date;
import java.util.UUID;

public class EmployeeResponseDto {

    private UUID id;
    private String fullName;
    private String companyName;
    private String phone;
    private String address;
    private String code;
    private Date dateOfBirth;

    public EmployeeResponseDto(UUID id, String fullName, String phone, String address, String code, String companyName, Date dateOfBirth) {
        this.id = id;
        this.fullName = fullName;
        this.phone = phone;
        this.address = address;
        this.code = code;
        this.companyName = companyName;
        this.dateOfBirth = dateOfBirth;
    }

    public UUID getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getCode() {
        return code;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

}
