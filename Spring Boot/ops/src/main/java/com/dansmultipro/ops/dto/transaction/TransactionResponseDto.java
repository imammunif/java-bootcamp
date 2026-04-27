package com.dansmultipro.ops.dto.transaction;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TransactionResponseDto {

    private String id;
    private String code;
    private String totalBill;
    private String accountNumber;
    private String statusName;
    private String customerName;
    private String gatewayName;
    private String productName;

}
