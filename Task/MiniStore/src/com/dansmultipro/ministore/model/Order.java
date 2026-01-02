package com.dansmultipro.ministore.model;

import java.time.LocalDateTime;
import java.util.List;

public class Order {

    private String sequence;
    private List<Product> products;
    private LocalDateTime dateTime;
    private Double grandTotal;

    public Order(String sequence, List<Product> products, LocalDateTime dateTime, Double grandTotal) {
        this.sequence = sequence;
        this.products = products;
        this.dateTime = dateTime;
        this.grandTotal = grandTotal;
    }

    public String getSequence() {
        return sequence;
    }

    public List<Product> getProducts() {
        return products;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public Double getGrandTotal() {
        return grandTotal;
    }
}
