package com.dansmultipro.ims.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "t_move_history")
public class MoveHistory extends BaseModel {

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Integer newQuantity;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private HistoryType historyType;

    public LocalDate getDate() {
        return date;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Integer getNewQuantity() {
        return newQuantity;
    }

    public Product getProduct() {
        return product;
    }

    public HistoryType getHistoryType() {
        return historyType;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setNewQuantity(Integer newQuantity) {
        this.newQuantity = newQuantity;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setHistoryType(HistoryType historyType) {
        this.historyType = historyType;
    }

}
