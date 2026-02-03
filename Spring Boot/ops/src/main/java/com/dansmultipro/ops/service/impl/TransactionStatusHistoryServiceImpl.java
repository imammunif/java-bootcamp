package com.dansmultipro.ops.service.impl;

import com.dansmultipro.ops.dto.transactionstatushistory.TransactionStatusHistoryResponseDto;
import com.dansmultipro.ops.exception.NotFoundException;
import com.dansmultipro.ops.model.GatewayUser;
import com.dansmultipro.ops.model.TransactionStatusHistory;
import com.dansmultipro.ops.repository.GatewayUserRepo;
import com.dansmultipro.ops.repository.TransactionStatusHistoryRepo;
import com.dansmultipro.ops.service.TransactionStatusHistoryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TransactionStatusHistoryServiceImpl extends BaseService implements TransactionStatusHistoryService {

    private final TransactionStatusHistoryRepo transactionStatusHistoryRepo;
    private final GatewayUserRepo gatewayUserRepo;

    public TransactionStatusHistoryServiceImpl(TransactionStatusHistoryRepo transactionStatusHistoryRepo, GatewayUserRepo gatewayUserRepo) {
        this.transactionStatusHistoryRepo = transactionStatusHistoryRepo;
        this.gatewayUserRepo = gatewayUserRepo;
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
        UUID userId = principalService.getPrincipal().getId();
        GatewayUser gatewayUser = gatewayUserRepo.findByUserId(userId).orElseThrow(
                () -> new NotFoundException("Gateway user not found")
        );

        List<TransactionStatusHistory> historyList = transactionStatusHistoryRepo.findAllByTransaction_GatewayId(gatewayUser.getGateway().getId());
        List<TransactionStatusHistoryResponseDto> historyResponseDtoList = new ArrayList<>();
        for (TransactionStatusHistory v : historyList) {
            TransactionStatusHistoryResponseDto responseDto = new TransactionStatusHistoryResponseDto(
                    v.getId(), v.getStatus().getName(), v.getTransaction().getCode());
            historyResponseDtoList.add(responseDto);
        }
        return historyResponseDtoList;
    }

}
