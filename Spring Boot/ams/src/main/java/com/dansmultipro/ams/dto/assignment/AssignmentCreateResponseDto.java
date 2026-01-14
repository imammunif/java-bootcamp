package com.dansmultipro.ams.dto.assignment;

import java.util.UUID;

public class AssignmentCreateResponseDto {

    private UUID id;

    public AssignmentCreateResponseDto(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

}
