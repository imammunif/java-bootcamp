package com.dansmultipro.ams.dto.assignment;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public class AssignmentRequestDto {

    private String targetLocationId;
    private String targetAssetId;
    private String targetEmployeeId;

    @NotEmpty(message = "Assignment's details is required")
    private List<String> assignmentDetialIdList;

    public String getTargetLocationId() {
        return targetLocationId;
    }

    public String getTargetAssetId() {
        return targetAssetId;
    }

    public String getTargetEmployeeId() {
        return targetEmployeeId;
    }

    public List<String> getAssignmentDetialIdList() {
        return assignmentDetialIdList;
    }

}
