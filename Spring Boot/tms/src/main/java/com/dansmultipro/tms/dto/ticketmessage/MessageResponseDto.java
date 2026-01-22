package com.dansmultipro.tms.dto.ticketmessage;

import java.util.UUID;

public class MessageResponseDto {

    private UUID id;
    private String userName;
    private String message;

    public MessageResponseDto(UUID id, String userName, String message) {
        this.id = id;
        this.userName = userName;
        this.message = message;
    }

    public UUID getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getDescription() {
        return message;
    }

}
