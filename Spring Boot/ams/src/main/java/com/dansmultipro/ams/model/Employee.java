package com.dansmultipro.ams.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "t_employee")
public class Employee extends BaseModel {

    @Column(nullable = false, length = 45)
    private String name;

    @Column(nullable = false)
    private Date dateOfBirth;

    @Column(nullable = false, length = 250)
    private String address;

    @Column(nullable = false, length = 20, unique = true)
    private String phone;

    @Column(nullable = false, length = 20, unique = true)
    private String code;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    @Column(length = 36)
    private Company company;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
