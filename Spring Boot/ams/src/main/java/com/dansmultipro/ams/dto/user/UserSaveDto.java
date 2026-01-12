package com.dansmultipro.ams.dto.user;

public class UserSaveDto {

    private String fullName;
    private String email;

    public String getEmail() {
        return email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

}
