package com.dansmultipro.tms.constant;

public enum StatusCode {

    OPEN("OPEN"),
    REOPEN("RSLV"),
    RESOLVED("CLSD"),
    CLOSED("REOP");

    private final String code;

    StatusCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
