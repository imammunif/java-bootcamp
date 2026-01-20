package com.dansmultipro.ams.dto.assignment;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class UpdateAssignmentRequestDto {

    @NotEmpty(message = "Assignment's details is required")
    private List<String> assignmentDetailIdList;

    @NotNull(message = "Version is required")
    private Integer version;

    public List<String> getAssignmentDetailIdList() {
        return assignmentDetailIdList;
    }

    public Integer getVersion() {
        return version;
    }

    public void setAssignmentDetailIdList(List<String> assignmentDetailIdList) {
        this.assignmentDetailIdList = assignmentDetailIdList;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

}
