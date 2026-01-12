package com.dansmultipro.ams.dto.assignment;

public class AssignmentResponse {

    private String id;
    private String code;
    private String assignDate;
    private String targetLocationName;
    private String targetAssetName;
    private String targetEmployeeName;
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

    public String getTargetLocationName() {
        return targetLocationName;
    }

    public String getTargetAssetName() {
        return targetAssetName;
    }

    public String getTargetEmployeeName() {
        return targetEmployeeName;
    }

    public Integer getVersion() {
        return version;
    }

}
