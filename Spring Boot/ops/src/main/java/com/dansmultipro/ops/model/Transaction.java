package com.dansmultipro.ops.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "t_transaction")
public class Transaction extends BaseModel {

    @Column(nullable = false, length = 10, unique = true)
    private String code;

    @Column(nullable = false)
    private BigDecimal totalBill;

    @Column(nullable = false, length = 20)
    private String accountNumber;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private TransactionStatus status;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private User customer;

    @ManyToOne
    @JoinColumn(name = "gateway_id", nullable = false)
    private Gateway gateway;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

}
