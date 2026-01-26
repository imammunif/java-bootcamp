package com.dansmultipro.ims.dto.agent;

import java.util.UUID;

public class AgentResponseDto {

    private UUID id;
    private String name;

    public AgentResponseDto(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
