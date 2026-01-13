package com.dansmultipro.ams.dto.user;

import java.util.UUID;

public class UserResponseDto {

    private UUID id;
    private String fullName;
    private String phone;
    private String address;
    private String roleName;

    public UserResponseDto(UUID id, String fullName, String phone, String address, String roleName) {
        this.id = id;
        this.fullName = fullName;
        this.phone = phone;
        this.address = address;
        this.roleName = roleName;
    }

    public UUID getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getRoleName() {
        return roleName;
    }

}
