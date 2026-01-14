package com.dansmultipro.ams.dto.employee;

import java.util.Date;

public class EmployeeRequestDto {

    private String fullName;
    private String companyId;
    private String code;
    private String phone;
    private String address;
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
