package com.dansmultipro.ops.constant;

public enum RoleCode {

    SA("SA"),
    CUSTOMER("CUST"),
    GATEWAY("GA");

    private final String code;

    RoleCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
