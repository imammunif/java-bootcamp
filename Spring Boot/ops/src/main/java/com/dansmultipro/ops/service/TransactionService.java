package com.dansmultipro.ops.service;

import com.dansmultipro.ops.dto.CreateResponseDto;
import com.dansmultipro.ops.dto.UpdateResponseDto;
import com.dansmultipro.ops.dto.transaction.CreateTransactionRequestDto;
import com.dansmultipro.ops.dto.transaction.TransactionResponseDto;
import com.dansmultipro.ops.dto.transaction.UpdateTransactionRequestDto;

import java.util.List;

public interface TransactionService {

    List<TransactionResponseDto> getAll();

    //CHECK NO USAGE
    TransactionResponseDto getById(String id);

    List<TransactionResponseDto> getAllByCustomerId();

    List<TransactionResponseDto> getAllByGatewayId();

    CreateResponseDto create(CreateTransactionRequestDto data);

    UpdateResponseDto update(String id, String code, UpdateTransactionRequestDto data);

}
