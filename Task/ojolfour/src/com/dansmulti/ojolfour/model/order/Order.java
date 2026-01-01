package com.dansmulti.ojolfour.model.order;

import com.dansmulti.ojolfour.model.constant.OrderType;

import java.time.LocalDateTime;

public abstract class Order {

    private OrderType type;
    private LocalDateTime dateTime;
    private String from;
    private String to;

    public Order(OrderType type, LocalDateTime dateTime, String from, String to) {
        this.type = type;
        this.dateTime = dateTime;
        this.from = from;
        this.to = to;
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

    public OrderType getType() {
        return type;
    }

}