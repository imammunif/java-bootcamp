package com.dansmultipro.ams.dto.assignment;

import java.util.List;

public class AssignmentRequestDto {

    private String targetLocationName;
    private String targetAssetName;
    private String targetEmployeeName;
    private List<String> assignmentList;

    public String getTargetLocationName() {
        return targetLocationName;
    }

    public String getTargetAssetName() {
        return targetAssetName;
    }

    public String getTargetEmployeeName() {
        return targetEmployeeName;
    }

}
