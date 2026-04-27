package com.dansmultipro.ops.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "t_user")
public class User extends BaseModel {

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 50, unique = true)
    private String email;

    @Column(nullable = false, length = 200)
    private String password;

    @Column(length = 6)
    private String activationCode;

    @Column(name = "is_active", nullable = false)
    private Boolean active;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private UserRole userRole;

}
