package com.dansmultipro.ops.service.impl;

import com.dansmultipro.ops.constant.ResponseMessage;
import com.dansmultipro.ops.constant.StatusCode;
import com.dansmultipro.ops.dto.CreateResponseDto;
import com.dansmultipro.ops.dto.UpdateResponseDto;
import com.dansmultipro.ops.dto.transaction.CreateTransactionRequestDto;
import com.dansmultipro.ops.dto.transaction.TransactionResponseDto;
import com.dansmultipro.ops.dto.transaction.UpdateTransactionRequestDto;
import com.dansmultipro.ops.exception.InvalidStatusException;
import com.dansmultipro.ops.exception.MissMatchException;
import com.dansmultipro.ops.exception.NotFoundException;
import com.dansmultipro.ops.model.*;
import com.dansmultipro.ops.repository.*;
import com.dansmultipro.ops.service.TransactionService;
import com.dansmultipro.ops.util.RandomGenerator;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TransactionServiceImpl extends BaseService implements TransactionService {

    private final TransactionRepo transactionRepo;
    private final UserRepo userRepo;
    private final TransactionStatusRepo transactionStatusRepo;
    private final GatewayRepo gatewayRepo;
    private final ProductRepo productRepo;
    private final TransactionStatusHistoryRepo transactionStatusHistoryRepo;

    public TransactionServiceImpl(TransactionRepo transactionRepo, UserRepo userRepo, TransactionStatusRepo transactionStatusRepo, GatewayRepo gatewayRepo, ProductRepo productRepo, TransactionStatusHistoryRepo transactionStatusHistoryRepo) {
        this.transactionRepo = transactionRepo;
        this.userRepo = userRepo;
        this.transactionStatusRepo = transactionStatusRepo;
        this.gatewayRepo = gatewayRepo;
        this.productRepo = productRepo;
        this.transactionStatusHistoryRepo = transactionStatusHistoryRepo;
    }

    @Override
    public List<TransactionResponseDto> getAll() {
        List<Transaction> transactionList = transactionRepo.findAll();
        List<TransactionResponseDto> transactionResponseDtoList = new ArrayList<>();
        for (Transaction v : transactionList) {
            TransactionResponseDto responseDto = new TransactionResponseDto(
                    v.getId().toString(), v.getCode(), v.getTotalBill().toString(),
                    v.getAccountNumber(), v.getStatus().getName(), v.getCustomer().getName(),
                    v.getGateway().getName(), v.getProduct().getName());
            transactionResponseDtoList.add(responseDto);
        }
        return transactionResponseDtoList;
    }

    @Override
    public TransactionResponseDto getById(String id) {
        UUID validId = validateUUID(id);
        Transaction transaction = transactionRepo.findById(validId).orElseThrow(
                () -> new NotFoundException("Transaction not found")
        );
        return new TransactionResponseDto(
                transaction.getId().toString(), transaction.getCode(), transaction.getTotalBill().toString(),
                transaction.getAccountNumber(), transaction.getStatus().getName(), transaction.getCustomer().getName(),
                transaction.getGateway().getName(), transaction.getProduct().getName());
    }

    @Override
    public List<TransactionResponseDto> getAllByCustomerId(String customerId) {
        UUID validId = validateUUID(customerId);
        userRepo.findById(validId).orElseThrow(
                () -> new NotFoundException("Customer not found")
        );
        List<Transaction> transactionList = transactionRepo.findByCustomerId(validId);
        List<TransactionResponseDto> transactionResponseDtoList = new ArrayList<>();
        for (Transaction v : transactionList) {
            TransactionResponseDto responseDto = new TransactionResponseDto(
                    v.getId().toString(), v.getCode(), v.getTotalBill().toString(),
                    v.getAccountNumber(), v.getStatus().getName(), v.getCustomer().getName(),
                    v.getGateway().getName(), v.getProduct().getName());
            transactionResponseDtoList.add(responseDto);
        }
        return transactionResponseDtoList;
    }

    @Override
    public List<TransactionResponseDto> getAllByGatewayId(String gatewayId) {
        UUID validId = validateUUID(gatewayId);
        gatewayRepo.findById(validId).orElseThrow(
                () -> new NotFoundException("Gateway not found")
        );
        List<Transaction> transactionList = transactionRepo.findByGatewayId(validId);
        List<TransactionResponseDto> transactionResponseDtoList = new ArrayList<>();
        for (Transaction v : transactionList) {
            TransactionResponseDto responseDto = new TransactionResponseDto(
                    v.getId().toString(), v.getCode(), v.getTotalBill().toString(),
                    v.getAccountNumber(), v.getStatus().getName(), v.getCustomer().getName(),
                    v.getGateway().getName(), v.getProduct().getName());
            transactionResponseDtoList.add(responseDto);
        }
        return transactionResponseDtoList;
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public CreateResponseDto create(CreateTransactionRequestDto data) {
        UUID gatewayId = validateUUID(data.getGatewayId());
        UUID productId = validateUUID(data.getProductId());
        User user = userRepo.findById(UUID.fromString("31b8d223-656a-4964-b390-1248cbd98e35")).orElseThrow(
                () -> new NotFoundException("User not found")
        );
        TransactionStatus status = transactionStatusRepo.findByCode(StatusCode.PROCESS.getCode()).orElseThrow(
                () -> new NotFoundException("Status not found")
        );
        Gateway gateway = gatewayRepo.findById(gatewayId).orElseThrow(
                () -> new NotFoundException("Gateway not found")
        );
        Product product = productRepo.findById(productId).orElseThrow(
                () -> new NotFoundException("Product not found")
        );
        TransactionStatus transactionStatus = transactionStatusRepo.findByCode(StatusCode.PROCESS.getCode()).orElseThrow(
                () -> new NotFoundException("Status is not found")
        );

        Transaction newTransaction = prepareForInsert(new Transaction());
        newTransaction.setCustomer(user);
        newTransaction.setStatus(status);
        newTransaction.setCode(RandomGenerator.randomizeCode(10));
        newTransaction.setGateway(gateway);
        newTransaction.setProduct(product);
        newTransaction.setTotalBill(data.getAmount());
        newTransaction.setAccountNumber(data.getAccountNumber());
        Transaction createdTransaction = transactionRepo.save(newTransaction);

        TransactionStatusHistory newStatusHistory = prepareForInsert(new TransactionStatusHistory());
        newStatusHistory.setStatus(transactionStatus);
        newStatusHistory.setTransaction(createdTransaction);
        transactionStatusHistoryRepo.save(newStatusHistory);

        return new CreateResponseDto(createdTransaction.getId(), ResponseMessage.CREATED.getMessage());
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public UpdateResponseDto update(String gatewayId, String id, String newStatus, UpdateTransactionRequestDto data) {
        gatewayRepo.findById(validateUUID(gatewayId)).orElseThrow(
                () -> new NotFoundException("Gateway not found")
        );
        Transaction transaction = transactionRepo.findById(validateUUID(id)).orElseThrow(
                () -> new NotFoundException("Transaction not found")
        );
        if (!transaction.getVersion().equals(data.getVersion())) {
            throw new MissMatchException("Version not match");
        }
        TransactionStatus transactionStatus = transactionStatusRepo.findByCode(newStatus).orElseThrow(
                () -> new NotFoundException("Status is not found")
        );

        Transaction updateTransaction = prepareForUpdate(transaction);
        String currentStatus = transaction.getStatus().getCode();

        if (newStatus.equals(StatusCode.PAID.getCode())) {
            if (currentStatus.equals(StatusCode.REJECTED.getCode())) {
                throw new InvalidStatusException("Rejected transaction can't be paid");
            } else if (currentStatus.equals(StatusCode.PAID.getCode())) {
                throw new InvalidStatusException("Transaction already paid");
            } else {
                updateTransaction.setStatus(transactionStatus);
            }
        }
        if (newStatus.equals(StatusCode.REJECTED.getCode())) {
            if (currentStatus.equals(StatusCode.PAID.getCode())) {
                throw new InvalidStatusException("Paid transaction can't be rejected");
            } else if (currentStatus.equals(StatusCode.REJECTED.getCode())) {
                throw new InvalidStatusException("Transaction already rejected");
            } else {
                updateTransaction.setStatus(transactionStatus);
            }
        }
        Transaction updatedTransaction = transactionRepo.saveAndFlush(updateTransaction);

        TransactionStatusHistory newStatusHistory = prepareForInsert(new TransactionStatusHistory());
        newStatusHistory.setStatus(transactionStatus);
        newStatusHistory.setTransaction(transaction);
        transactionStatusHistoryRepo.save(newStatusHistory);

        return new UpdateResponseDto(updatedTransaction.getVersion(), ResponseMessage.UPDATED.getMessage());
    }

}
