package com.dansmultipro.ops.controller;

import com.dansmultipro.ops.dto.transactionstatus.TransactionStatusResponseDto;
import com.dansmultipro.ops.service.TransactionStatusService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("transaction-statuses")
public class TransactionStatusController {

    private final TransactionStatusService transactionStatusService;

    public TransactionStatusController(TransactionStatusService transactionStatusService) {
        this.transactionStatusService = transactionStatusService;
    }

    @GetMapping
    public ResponseEntity<List<TransactionStatusResponseDto>> getAllTransactionStatuses() {
        List<TransactionStatusResponseDto> res = transactionStatusService.getAll();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<TransactionStatusResponseDto> getTransactionStatusById(@PathVariable String id) {
        TransactionStatusResponseDto res = transactionStatusService.getById(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
