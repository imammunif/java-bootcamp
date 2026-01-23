package com.dansmultipro.tms.dto.ticket;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateTicketRequestDto {

    @NotBlank(message = "Ticket title is required")
    @Size(max = 50, message = "Title length exceeds limit, max 50 characters")
    private String title;

    @NotBlank(message = "Ticket description is required")
    private String description;

    @NotBlank(message = "Product is required")
    private String productId;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getProductId() {
        return productId;
    }

}
