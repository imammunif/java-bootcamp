package com.dansmultipro.tms.dto.message;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateMessageRequestDto {

    @NotBlank(message = "User is required")
    private String userId;

    @NotBlank(message = "Ticket is required")
    private String ticketId;

    @NotBlank(message = "Message is required")
    @Size(max = 300, message = "Message length exceeds limit, max 300 characters")
    private String description;

    public String getUserId() {
        return userId;
    }

    public String getTicketId() {
        return ticketId;
    }

    public String getDescription() {
        return description;
    }

}
