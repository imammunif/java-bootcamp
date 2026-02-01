package com.dansmultipro.ops.repository;

import com.dansmultipro.ops.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TransactionRepo extends JpaRepository<Transaction, UUID> {

    List<Transaction> findByCustomerId(UUID customerId);

    List<Transaction> findByGatewayId(UUID gatewayId);

    Boolean existsByCustomerId(UUID id);

}
