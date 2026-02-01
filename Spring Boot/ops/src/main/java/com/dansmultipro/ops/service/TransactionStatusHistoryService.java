package com.dansmultipro.ops.service;

import com.dansmultipro.ops.dto.transactionstatushistory.TransactionStatusHistoryResponseDto;

import java.util.List;

public interface TransactionStatusHistoryService {

    List<TransactionStatusHistoryResponseDto> getAllByTransactionId(String id);

    List<TransactionStatusHistoryResponseDto> getAllByCustomerId(String customerId, String transactionId);

    List<TransactionStatusHistoryResponseDto> getAllByGatewayId(String gatewayId, String transactionId);

}
