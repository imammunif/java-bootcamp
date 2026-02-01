package com.dansmultipro.ops.constant;

public enum StatusCode {

    PROCESS("PRC"),
    PAID("PAY"),
    REJECTED("RJC");

    private final String code;

    StatusCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
