package com.dansmultipro.ops.repository;

import com.dansmultipro.ops.model.TransactionStatusHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TransactionStatusHistoryRepo extends JpaRepository<TransactionStatusHistory, UUID> {

    List<TransactionStatusHistory> findAllByTransactionId(UUID transactionId);

}
