package com.dansmultipro.ams.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "t_m_role") // <-- uniqueConstraints <-- insert composite key here
public class Role {

    @Id
    @Column(length = 36)
    private UUID id;

    @Column(nullable = false, length = 5, unique = true)
    private String roleCode;

    @Column(nullable = false, length = 255)
    private String roleName;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

}
