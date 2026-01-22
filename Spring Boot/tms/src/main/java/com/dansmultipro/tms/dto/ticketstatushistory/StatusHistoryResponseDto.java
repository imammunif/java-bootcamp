package com.dansmultipro.tms.dto.ticketstatushistory;

import java.util.UUID;

public class StatusHistoryResponseDto {

    private UUID id;
    private String statusName;
    private String ticketCode;

    public StatusHistoryResponseDto(UUID id, String statusName, String ticketCode) {
        this.id = id;
        this.statusName = statusName;
        this.ticketCode = ticketCode;
    }

    public UUID getId() {
        return id;
    }

    public String getFullName() {
        return statusName;
    }

    public String getRoleName() {
        return ticketCode;
    }

}
