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
@RequestMapping("transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public ResponseEntity<List<TransactionResponseDto>> getAllTransactions() {
        List<TransactionResponseDto> res = transactionService.getAll();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/customer")
    public ResponseEntity<List<TransactionResponseDto>> getAllByCustomerId() {
        List<TransactionResponseDto> res = transactionService.getAllByCustomerId();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/gateway")
    public ResponseEntity<List<TransactionResponseDto>> getAllByGatewayId() {
        List<TransactionResponseDto> res = transactionService.getAllByGatewayId();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CreateResponseDto> create(
            @RequestBody @Valid CreateTransactionRequestDto data
    ) {
        CreateResponseDto res = transactionService.create(data);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/{status_code}")
    public ResponseEntity<UpdateResponseDto> update(
            @PathVariable String id,
            @PathVariable("status_code") String code,
            @RequestBody @Valid UpdateTransactionRequestDto data
    ) {
        UpdateResponseDto res = transactionService.update(id, code, data);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
