package com.dansmultipro.ops.dto.transactionstatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class TransactionStatusResponseDto {

    private UUID id;
    private String name;
    private String code;

}
