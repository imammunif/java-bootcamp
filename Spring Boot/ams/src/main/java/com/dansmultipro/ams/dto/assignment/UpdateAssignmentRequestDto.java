package com.dansmultipro.ams.dto.assignment;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public class UpdateAssignmentRequestDto {

    private List<String> assignmentDetailIdList;

    public List<String> getAssignmentDetailIdList() {
        return assignmentDetailIdList;
    }

    @NotBlank(message = "Version is required")
    private String version;

    public String getVersion() {
        return version;
    }

}
