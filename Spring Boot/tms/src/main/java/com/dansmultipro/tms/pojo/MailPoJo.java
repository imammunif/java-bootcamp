package com.dansmultipro.tms.pojo;

public class MailPoJo {

    private String emailAddress;
    private String emailBody;

    public MailPoJo(String emailAddress, String emailBody) {
        this.emailAddress = emailAddress;
        this.emailBody = emailBody;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getEmailBody() {
        return emailBody;
    }
    
}
