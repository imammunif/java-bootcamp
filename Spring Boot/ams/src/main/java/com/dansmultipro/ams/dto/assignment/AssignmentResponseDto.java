package com.dansmultipro.ams.dto.assignment;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class AssignmentResponseDto {

    private UUID id;
    private String code;
    private LocalDate assignDate;
    private String targetName;
    private List<String> assignmentList;
    private Integer version;

    public AssignmentResponseDto(UUID id, String code, LocalDate assignDate) {
        this.id = id;
        this.code = code;
        this.assignDate = assignDate;
    }

    public UUID getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public LocalDate getAssignDate() {
        return assignDate;
    }

    public List<String> getAssignmentList() {
        return assignmentList;
    }

    public Integer getVersion() {
        return version;
    }

}
