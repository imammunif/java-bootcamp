package com.dansmultipro.tms.constant;

public enum StatusCode {

    OPEN("OPEN"),
    REOPEN("REOP"),
    RESOLVED("RSLV"),
    CLOSED("CLSD");

    private final String code;

    StatusCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
