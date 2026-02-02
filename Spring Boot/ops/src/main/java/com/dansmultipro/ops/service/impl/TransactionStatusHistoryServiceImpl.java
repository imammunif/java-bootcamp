package com.dansmultipro.ops.service.impl;

import com.dansmultipro.ops.dto.transactionstatushistory.TransactionStatusHistoryResponseDto;
import com.dansmultipro.ops.exception.NotFoundException;
import com.dansmultipro.ops.model.TransactionStatusHistory;
import com.dansmultipro.ops.repository.GatewayRepo;
import com.dansmultipro.ops.repository.TransactionStatusHistoryRepo;
import com.dansmultipro.ops.service.TransactionStatusHistoryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TransactionStatusHistoryServiceImpl extends BaseService implements TransactionStatusHistoryService {

    private final TransactionStatusHistoryRepo transactionStatusHistoryRepo;
    private final GatewayRepo gatewayRepo;

    public TransactionStatusHistoryServiceImpl(TransactionStatusHistoryRepo transactionStatusHistoryRepo, GatewayRepo gatewayRepo) {
        this.transactionStatusHistoryRepo = transactionStatusHistoryRepo;
        this.gatewayRepo = gatewayRepo;
    }

    @Override
    public List<TransactionStatusHistoryResponseDto> getAll() {
        List<TransactionStatusHistory> historyList = transactionStatusHistoryRepo.findAll();
        List<TransactionStatusHistoryResponseDto> historyDtoList = new ArrayList<>();
        for (TransactionStatusHistory v : historyList) {
            TransactionStatusHistoryResponseDto responseDto = new TransactionStatusHistoryResponseDto(
                    v.getId(), v.getStatus().getName(), v.getTransaction().getCode());
            historyDtoList.add(responseDto);
        }
        return historyDtoList;
    }

    @Override
    public List<TransactionStatusHistoryResponseDto> getAllByGatewayId() {
        UUID gatewayId = principalService.getPrincipal().getId();
        gatewayRepo.findById(gatewayId).orElseThrow(
                () -> new NotFoundException("Gateway not found")
        );

        List<TransactionStatusHistory> historyList = transactionStatusHistoryRepo.findAllByTransaction_GatewayId(gatewayId);
        List<TransactionStatusHistoryResponseDto> historyResponseDtoList = new ArrayList<>();
        for (TransactionStatusHistory v : historyList) {
            TransactionStatusHistoryResponseDto responseDto = new TransactionStatusHistoryResponseDto(
                    v.getId(), v.getStatus().getName(), v.getTransaction().getCode());
            historyResponseDtoList.add(responseDto);
        }
        return historyResponseDtoList;
    }

}
