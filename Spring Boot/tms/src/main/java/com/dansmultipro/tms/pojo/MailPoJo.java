package com.dansmultipro.tms.pojo;

public class MailPoJo {

    private String email;
    private String ticketCode;

    public MailPoJo(String email, String ticketCode) {
        this.email = email;
        this.ticketCode = ticketCode;
    }

    public String getEmail() {
        return email;
    }

    public String getTicketCode() {
        return ticketCode;
    }

}
