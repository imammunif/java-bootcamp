package com.dansmultipro.ams.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_m_role") // <-- uniqueConstraints <-- insert composite key here
public class Role extends BaseModel {

    @Column(nullable = false, length = 5, unique = true)
    private String roleCode;

    @Column(nullable = false, length = 20)
    private String roleName;

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
