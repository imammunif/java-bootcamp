package com.dansmultipro.ops.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "t_m_user_role")
public class UserRole extends BaseModel {

    @Column(nullable = false, length = 5, unique = true)
    private String code;

    @Column(nullable = false, length = 20)
    private String name;

}
