package com.dansmultipro.ams.dto.assignment;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class AssignmentResponseDto {

    private UUID id;
    private String code;
    private LocalDate assignDate;
    private String targetName;
    private List<String> assignmentList;

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

}
