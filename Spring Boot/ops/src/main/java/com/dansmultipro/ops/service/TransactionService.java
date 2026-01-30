package com.dansmultipro.ops.service;

import com.dansmultipro.ops.dto.CreateResponseDto;
import com.dansmultipro.ops.dto.UpdateResponseDto;
import com.dansmultipro.ops.dto.transaction.CreateTransactionRequestDto;
import com.dansmultipro.ops.dto.transaction.TransactionResponseDto;
import com.dansmultipro.ops.dto.transaction.UpdateTransactionRequestDto;

import java.util.List;

public interface TransactionService {

    List<TransactionResponseDto> getAll();

    TransactionResponseDto getById(String id);

    CreateResponseDto create(CreateTransactionRequestDto requestDto);

    UpdateResponseDto update(String id, String statusCode, UpdateTransactionRequestDto requestDto);

}
