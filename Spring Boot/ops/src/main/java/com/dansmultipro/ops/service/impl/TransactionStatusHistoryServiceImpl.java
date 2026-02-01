package com.dansmultipro.ops.service.impl;

import com.dansmultipro.ops.dto.transactionstatushistory.TransactionStatusHistoryResponseDto;
import com.dansmultipro.ops.exception.NotFoundException;
import com.dansmultipro.ops.model.Transaction;
import com.dansmultipro.ops.model.TransactionStatusHistory;
import com.dansmultipro.ops.repository.GatewayRepo;
import com.dansmultipro.ops.repository.TransactionRepo;
import com.dansmultipro.ops.repository.TransactionStatusHistoryRepo;
import com.dansmultipro.ops.repository.UserRepo;
import com.dansmultipro.ops.service.TransactionStatusHistoryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TransactionStatusHistoryServiceImpl extends BaseService implements TransactionStatusHistoryService {

    private final TransactionRepo transactionRepo;
    private final TransactionStatusHistoryRepo transactionStatusHistoryRepo;
    private final UserRepo userRepo;
    private final GatewayRepo gatewayRepo;


    public TransactionStatusHistoryServiceImpl(TransactionRepo transactionRepo, TransactionStatusHistoryRepo transactionStatusHistoryRepo, UserRepo userRepo, GatewayRepo gatewayRepo) {
        this.transactionRepo = transactionRepo;
        this.transactionStatusHistoryRepo = transactionStatusHistoryRepo;
        this.userRepo = userRepo;
        this.gatewayRepo = gatewayRepo;
    }

    @Override
    public List<TransactionStatusHistoryResponseDto> getAllByTransactionId(String id) {
        UUID validId = validateUUID(id);
        Transaction transaction = transactionRepo.findById(validId).orElseThrow(
                () -> new NotFoundException("Transaction not found")
        );
        List<TransactionStatusHistoryResponseDto> result = transactionStatusHistoryRepo.findAllByTransactionId(validId).stream()
                .map(v -> new TransactionStatusHistoryResponseDto(
                        v.getId(), v.getStatus().getName(), v.getTransaction().getCode()))
                .toList();
        return result;
    }

    @Override
    public List<TransactionStatusHistoryResponseDto> getAllByCustomerId(String customerId, String transactionId) {
        userRepo.findById(validateUUID(customerId)).orElseThrow(
                () -> new NotFoundException("Customer not found")
        );
        UUID validId = validateUUID(transactionId);
        transactionRepo.findById(validId).orElseThrow(
                () -> new NotFoundException("Transaction not found")
        );
        List<TransactionStatusHistory> historyList = transactionStatusHistoryRepo.findAllByTransactionId(validId);
        List<TransactionStatusHistoryResponseDto> historyResponseDtoList = new ArrayList<>();
        for (TransactionStatusHistory v : historyList) {
            TransactionStatusHistoryResponseDto responseDto = new TransactionStatusHistoryResponseDto(
                    v.getId(), v.getStatus().getName(), v.getTransaction().getCode());
            historyResponseDtoList.add(responseDto);
        }
        return historyResponseDtoList;
    }

    @Override
    public List<TransactionStatusHistoryResponseDto> getAllByGatewayId(String gatewayId, String transactionId) {
        gatewayRepo.findById(validateUUID(gatewayId)).orElseThrow(
                () -> new NotFoundException("Customer not found")
        );
        UUID validId = validateUUID(transactionId);
        transactionRepo.findById(validId).orElseThrow(
                () -> new NotFoundException("Transaction not found")
        );
        List<TransactionStatusHistory> historyList = transactionStatusHistoryRepo.findAllByTransactionId(validId);
        List<TransactionStatusHistoryResponseDto> historyResponseDtoList = new ArrayList<>();
        for (TransactionStatusHistory v : historyList) {
            TransactionStatusHistoryResponseDto responseDto = new TransactionStatusHistoryResponseDto(
                    v.getId(), v.getStatus().getName(), v.getTransaction().getCode());
            historyResponseDtoList.add(responseDto);
        }
        return historyResponseDtoList;
    }

}
