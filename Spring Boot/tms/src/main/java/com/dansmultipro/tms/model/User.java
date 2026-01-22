package com.dansmultipro.tms.model;

import jakarta.persistence.*;

@Entity
@Table(name = "t_m_user")
public class User extends BaseModel {

    @Column(nullable = false, length = 50)
    private String fullName;

    @Column(nullable = false, length = 50, unique = true)
    private String email;

    @Column(nullable = false, length = 200)
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private UserRole userRole;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public Company getCompany() {
        return company;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

}
