package com.dansmultipro.tms.dto.message;

import java.util.UUID;

public class MessageResponseDto {

    private UUID id;
    private String userName;
    private String description;

    public MessageResponseDto(UUID id, String userName, String description) {
        this.id = id;
        this.userName = userName;
        this.description = description;
    }

    public UUID getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getDescription() {
        return description;
    }

}
