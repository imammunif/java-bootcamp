package com.dansmultipro.ams.dto.employee;

import java.util.Date;

public class EmployeeResponse {

    private String id;
    private String fullName;
    private String companyName;
    private String phone;
    private String address;
    private String code;
    private Date dateOfBirth;

    public String getId() {
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
