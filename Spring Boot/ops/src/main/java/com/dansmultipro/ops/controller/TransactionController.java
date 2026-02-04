package com.dansmultipro.ops.controller;

import com.dansmultipro.ops.dto.CreateResponseDto;
import com.dansmultipro.ops.dto.UpdateResponseDto;
import com.dansmultipro.ops.dto.transaction.CreateTransactionRequestDto;
import com.dansmultipro.ops.dto.transaction.TransactionResponseDto;
import com.dansmultipro.ops.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAuthority('SA')")
    public ResponseEntity<List<TransactionResponseDto>> getAllTransactions() {
        List<TransactionResponseDto> res = transactionService.getAll();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/customers")
    @PreAuthorize("hasAuthority('CUST')")
    public ResponseEntity<List<TransactionResponseDto>> getAllByCustomerId() {
        List<TransactionResponseDto> res = transactionService.getAllByCustomerId();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/gateways")
    @PreAuthorize("hasAuthority('GA')")
    public ResponseEntity<List<TransactionResponseDto>> getAllByGatewayId() {
        List<TransactionResponseDto> res = transactionService.getAllByGatewayId();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CUST')")
    public ResponseEntity<CreateResponseDto> create(
            @RequestBody @Valid CreateTransactionRequestDto data
    ) {
        CreateResponseDto res = transactionService.create(data);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('GA')")
    @PutMapping
    public ResponseEntity<UpdateResponseDto> update(
            @RequestParam String id,
            @RequestParam String action,
            @RequestParam Integer version
    ) {
        UpdateResponseDto res = transactionService.update(id, action, version);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
