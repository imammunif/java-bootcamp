package com.dansmultipro.tms.dto.ticketmessage;

import jakarta.validation.constraints.NotBlank;

public class CreateMessageRequestDto {

    @NotBlank(message = "Message is required")
    private String message;

    public String getMessage() {
        return message;
    }

}
