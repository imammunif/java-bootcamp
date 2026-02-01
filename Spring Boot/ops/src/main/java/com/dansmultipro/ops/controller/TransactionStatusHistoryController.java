package com.dansmultipro.ops.controller;

import com.dansmultipro.ops.dto.transactionstatushistory.TransactionStatusHistoryResponseDto;
import com.dansmultipro.ops.service.TransactionStatusHistoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TransactionStatusHistoryController {

    private final TransactionStatusHistoryService transactionStatusHistoryService;

    public TransactionStatusHistoryController(TransactionStatusHistoryService transactionStatusHistoryService) {
        this.transactionStatusHistoryService = transactionStatusHistoryService;
    }

    @GetMapping("transactions/{transaction_id}")
    public ResponseEntity<List<TransactionStatusHistoryResponseDto>> getByTransactionId(
            @PathVariable("transaction_id") String transactionId
    ) {
        List<TransactionStatusHistoryResponseDto> res = transactionStatusHistoryService.getAllByTransactionId(transactionId);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("users/{customer_id}/transactions/{transaction_id}")
    public ResponseEntity<List<TransactionStatusHistoryResponseDto>> getByCustomerId(
            @PathVariable("customer_id") String customerId,
            @PathVariable("transaction_id") String transactionId
    ) {
        List<TransactionStatusHistoryResponseDto> res = transactionStatusHistoryService.getAllByCustomerId(customerId, transactionId);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("gateways/{gateway_id}/transactions/{transaction_id}")
    public ResponseEntity<List<TransactionStatusHistoryResponseDto>> getByGatewayId(
            @PathVariable("gateway_id") String gatewayId,
            @PathVariable("transaction_id") String transactionId
    ) {
        List<TransactionStatusHistoryResponseDto> res = transactionStatusHistoryService.getAllByGatewayId(gatewayId, transactionId);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
