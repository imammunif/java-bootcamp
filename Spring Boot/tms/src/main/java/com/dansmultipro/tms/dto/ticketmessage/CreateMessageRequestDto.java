package com.dansmultipro.tms.dto.ticketmessage;

import jakarta.validation.constraints.NotBlank;

public class CreateMessageRequestDto {

    @NotBlank(message = "User is required")
    private String userId;

    @NotBlank(message = "Ticket is required")
    private String ticketId;

    @NotBlank(message = "Message is required")
    private String message;

    public String getUserId() {
        return userId;
    }

    public String getTicketId() {
        return ticketId;
    }

    public String getMessage() {
        return message;
    }

}
