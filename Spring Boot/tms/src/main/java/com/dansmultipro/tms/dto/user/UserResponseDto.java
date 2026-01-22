package com.dansmultipro.tms.dto.user;

import java.util.UUID;

public class UserResponseDto {

    private UUID id;
    private String email;
    private String fullName;
    private String roleName;

    public UserResponseDto(UUID id, String email, String fullName, String roleName) {
        this.id = id;
        this.email = email;
        this.fullName = fullName;
        this.roleName = roleName;
    }

    public UUID getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFullName() {
        return fullName;
    }

    public String getRoleName() {
        return roleName;
    }

}
