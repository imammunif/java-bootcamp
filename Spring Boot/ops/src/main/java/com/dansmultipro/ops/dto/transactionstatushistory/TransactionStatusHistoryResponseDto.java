package com.dansmultipro.ops.dto.transactionstatushistory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionStatusHistoryResponseDto {

    private UUID id;
    private String statusName;
    private String transactionCode;
    private String createdAt;

}
