package com.dansmultipro.ims.dto.moveout;

import java.util.UUID;

public class MoveOutResponseDto {

    private UUID id;
    private String code;
    private String date;
    private String agentName;

    public MoveOutResponseDto(UUID id, String code, String date, String agentName) {
        this.id = id;
        this.code = code;
        this.date = date;
        this.agentName = agentName;
    }

    public UUID getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getDate() {
        return date;
    }

    public String getAgentName() {
        return agentName;
    }

}
