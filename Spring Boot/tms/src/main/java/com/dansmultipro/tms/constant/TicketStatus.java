package com.dansmultipro.tms.constant;

public enum TicketStatus {

    OPEN("OPEN"),
    REOPEN("RSLV"),
    RESOLVED("CLSD"),
    CLOSED("REOP");

    private final String code;

    TicketStatus(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
