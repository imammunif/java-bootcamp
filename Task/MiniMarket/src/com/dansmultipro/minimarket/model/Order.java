package com.dansmultipro.minimarket.model;

import java.time.LocalDateTime;

public class Order {

    private String sequence;
    private LocalDateTime dateTime;
    private Double grandTotal;

    public Order(String sequence, LocalDateTime dateTime, Double grandTotal) {
        this.sequence = sequence;
        this.dateTime = dateTime;
        this.grandTotal = grandTotal;
    }

    public String getSequence() {
        return sequence;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public Double getGrandTotal() {
        return grandTotal;
    }
}
