package com.dansmultipro.ops.controller;

import com.dansmultipro.ops.dto.CreateResponseDto;
import com.dansmultipro.ops.dto.UpdateResponseDto;
import com.dansmultipro.ops.dto.transaction.CreateTransactionRequestDto;
import com.dansmultipro.ops.dto.transaction.TransactionResponseDto;
import com.dansmultipro.ops.dto.transaction.UpdateTransactionRequestDto;
import com.dansmultipro.ops.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("transactions")
    public ResponseEntity<List<TransactionResponseDto>> getAllTransactions() {
        List<TransactionResponseDto> res = transactionService.getAll();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("users/{customer_id}/transactions")
    public ResponseEntity<List<TransactionResponseDto>> getAllByCustomerId(
            @PathVariable("customer_id") String customerId
    ) {
        List<TransactionResponseDto> res = transactionService.getAllByCustomerId(customerId);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("gateways/{gateway_id}/transactions")
    public ResponseEntity<List<TransactionResponseDto>> getAllByGatewayId(
            @PathVariable("gateway_id") String gatewayId
    ) {
        List<TransactionResponseDto> res = transactionService.getAllByGatewayId(gatewayId);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping("transactions")
    public ResponseEntity<CreateResponseDto> create(
            @RequestBody @Valid CreateTransactionRequestDto data
    ) {
        CreateResponseDto res = transactionService.create(data);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PutMapping("gateways/{gateway_id}/transactions/{id}/{status_code}")
    public ResponseEntity<UpdateResponseDto> update(
            @PathVariable("gateway_id") String gatewayId,
            @PathVariable String id,
            @PathVariable("status_code") String code,
            @RequestBody @Valid UpdateTransactionRequestDto data
    ) {
        UpdateResponseDto res = transactionService.update(gatewayId, id, code, data);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
