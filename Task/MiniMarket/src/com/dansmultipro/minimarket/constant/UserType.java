package com.dansmultipro.minimarket.constant;

public enum UserType {

    BUYER("buyer", "buyer"),
    SELLER("seller", "seller");

    private final String username;
    private final String password;

    UserType(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
