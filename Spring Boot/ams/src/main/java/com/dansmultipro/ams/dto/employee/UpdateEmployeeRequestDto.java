package com.dansmultipro.ams.dto.employee;

import java.util.Date;

public class UpdateEmployeeRequestDto {

    private String fullName;
    private String phone;
    private String address;
    private Date dateOfBirth;

    public String getFullName() {
        return fullName;
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

}
