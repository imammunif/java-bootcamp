package com.dansmultipro.ams.dto.assignment;

import java.util.List;

public class AssignmentResponseDto {

    private String id;
    private String code;
    private String assignDate;
    private String targetName;
    private List<String> assignmentList;
    private Integer version;

    public String getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getAssignDate() {
        return assignDate;
    }

    public List<String> getAssignmentList() {
        return assignmentList;
    }

    public Integer getVersion() {
        return version;
    }

}
