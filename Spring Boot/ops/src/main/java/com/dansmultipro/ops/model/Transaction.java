package com.dansmultipro.ops.model;

import jakarta.persistence.*;

@Entity
@Table(name = "t_transaction")
public class Transaction extends BaseModel {

    @Column(nullable = false, length = 5, unique = true)
    private String code;

    @Column(nullable = false)
    private Double totalBill;

    @Column(nullable = false, length = 20)
    private Integer virtualNumber;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private TransactionStatus status;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private User customer;

    @ManyToOne
    @JoinColumn(name = "gateway_id", nullable = false)
    private User gateway;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    public String getCode() {
        return code;
    }

    public Double getTotalBill() {
        return totalBill;
    }

    public Integer getVirtualNumber() {
        return virtualNumber;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public User getCustomer() {
        return customer;
    }

    public User getGateway() {
        return gateway;
    }

    public Product getProduct() {
        return product;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setTotalBill(Double totalBill) {
        this.totalBill = totalBill;
    }

    public void setVirtualNumber(Integer virtualNumber) {
        this.virtualNumber = virtualNumber;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public void setGateway(User gateway) {
        this.gateway = gateway;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

}
