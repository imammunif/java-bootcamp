package com.dansmulti.ojolfour.model.order;

import java.time.LocalDateTime;

public abstract class Order {
    private LocalDateTime dateTime;
    private String from;
    private String to;
    // private double totalBill;
    // private Driver driver;

    public Order(LocalDateTime dateTime, String from, String to) {
        this.dateTime = dateTime;
        this.from = from;
        this.to = to;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }
}
