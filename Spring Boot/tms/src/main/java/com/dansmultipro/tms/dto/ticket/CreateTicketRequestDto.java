package com.dansmultipro.tms.dto.ticket;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateTicketRequestDto {

    @NotBlank(message = "Ticket title is required")
    @Size(max = 100, message = "Title length exceeds limit, max 100 characters")
    private String title;

    @NotBlank(message = "Ticket description is required")
    @Size(max = 300, message = "Description length exceeds limit, max 300 characters")
    private String description;

    @NotBlank(message = "Customer is required")
    private String customerId;

    @NotBlank(message = "Product is required")
    private String productId;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getProductId() {
        return productId;
    }

}
