package com.dansmultipro.ams.model;

import jakarta.persistence.*;

@Entity
@Table(name = "t_user")
public class User extends BaseModel {

    @Column(nullable = false, length = 50, unique = true)
    private String email;

    @Column(nullable = false, length = 200)
    private String password;

    @ManyToOne
    @JoinColumn(name="role_id", nullable = false)
    @Column(length = 36)
    private Role role;

    @OneToOne
    @JoinColumn(name="employee_id", nullable = false)
    @Column(length = 36)
    private Employee employee;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
