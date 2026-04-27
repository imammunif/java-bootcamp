package com.dansmultipro.ops.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "t_transaction_status_history")
public class TransactionStatusHistory extends BaseModel {

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private TransactionStatus status;

    @ManyToOne
    @JoinColumn(name = "transaction_id", nullable = false)
    private Transaction transaction;

}
