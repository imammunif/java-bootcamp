package com.dansmultipro.ops.service;

import com.dansmultipro.ops.dto.CreateResponseDto;
import com.dansmultipro.ops.dto.UpdateResponseDto;
import com.dansmultipro.ops.dto.transaction.CreateTransactionRequestDto;
import com.dansmultipro.ops.dto.transaction.TransactionResponseDto;

import java.util.List;

public interface TransactionService {

    List<TransactionResponseDto> getAll();

    List<TransactionResponseDto> getAllByCustomerId();

    List<TransactionResponseDto> getAllByGatewayId();

    CreateResponseDto create(CreateTransactionRequestDto data);

    UpdateResponseDto update(String id, String action, Integer version);

}
