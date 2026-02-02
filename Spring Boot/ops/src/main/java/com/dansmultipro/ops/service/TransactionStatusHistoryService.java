package com.dansmultipro.ops.service;

import com.dansmultipro.ops.dto.transactionstatushistory.TransactionStatusHistoryResponseDto;

import java.util.List;

public interface TransactionStatusHistoryService {

    List<TransactionStatusHistoryResponseDto> getAll();

    List<TransactionStatusHistoryResponseDto> getAllByGatewayId();

}
