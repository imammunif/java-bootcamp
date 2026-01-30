package com.dansmultipro.ops.dto.transactionstatushistory;

import java.util.UUID;

public class TransactionStatusHistoryResponseDto {

    private UUID id;
    private String statusName;
    private String transactionCode;

    public TransactionStatusHistoryResponseDto(UUID id, String statusName, String ticketCode) {
        this.id = id;
        this.statusName = statusName;
        this.transactionCode = ticketCode;
    }

    public UUID getId() {
        return id;
    }

    public String getStatusName() {
        return statusName;
    }

    public String getTransactionCode() {
        return transactionCode;
    }

}
