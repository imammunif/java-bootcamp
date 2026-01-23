package com.dansmultipro.tms.pojo;

public class MailPoJo {

    private String email;
    private String assignmentCode;

    public MailPoJo(String email, String assignmentCode) {
        this.email = email;
        this.assignmentCode = assignmentCode;
    }

    public String getEmail() {
        return email;
    }

    public String getAssignmentCode() {
        return assignmentCode;
    }

}
