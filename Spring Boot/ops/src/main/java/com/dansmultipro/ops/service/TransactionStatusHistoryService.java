package com.dansmultipro.ops.service;

import com.dansmultipro.ops.dto.PaginatedResponseDto;
import com.dansmultipro.ops.dto.transactionstatushistory.TransactionStatusHistoryResponseDto;

import java.util.List;

public interface TransactionStatusHistoryService {

    PaginatedResponseDto<TransactionStatusHistoryResponseDto> getAll(Integer page, Integer size);

    PaginatedResponseDto<TransactionStatusHistoryResponseDto> getAllByGatewayId(Integer page, Integer size);

}
