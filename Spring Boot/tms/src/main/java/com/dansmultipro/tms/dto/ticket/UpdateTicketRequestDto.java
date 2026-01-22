package com.dansmultipro.tms.dto.ticket;

import jakarta.validation.constraints.NotNull;

public class UpdateTicketRequestDto {

    @NotNull(message = "Version is required")
    private Integer version;

}
