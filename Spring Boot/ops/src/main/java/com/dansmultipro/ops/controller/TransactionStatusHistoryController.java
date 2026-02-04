package com.dansmultipro.ops.controller;

import com.dansmultipro.ops.dto.transactionstatushistory.TransactionStatusHistoryResponseDto;
import com.dansmultipro.ops.service.TransactionStatusHistoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("histories")
public class TransactionStatusHistoryController {

    private final TransactionStatusHistoryService transactionStatusHistoryService;

    public TransactionStatusHistoryController(TransactionStatusHistoryService transactionStatusHistoryService) {
        this.transactionStatusHistoryService = transactionStatusHistoryService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('SA')")
    public ResponseEntity<List<TransactionStatusHistoryResponseDto>> getAll() {
        List<TransactionStatusHistoryResponseDto> res = transactionStatusHistoryService.getAll();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/gateways")
    @PreAuthorize("hasAuthority('GA')")
    public ResponseEntity<List<TransactionStatusHistoryResponseDto>> getByGatewayId() {
        List<TransactionStatusHistoryResponseDto> res = transactionStatusHistoryService.getAllByGatewayId();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
