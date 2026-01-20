package com.dansmultipro.ams.dto.assignment;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public class UpdateAssignmentRequestDto {

    private List<String> assignmentDetailIdList;

    public List<String> getAssignmentDetailIdList() {
        return assignmentDetailIdList;
    }

    @NotNull(message = "Version is required")
    private Integer version;

    public Integer getVersion() {
        return version;
    }

}
