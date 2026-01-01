package com.dansmulti.ojolfour.model.order;

import com.dansmulti.ojolfour.model.constant.OrderType;

import java.time.LocalDateTime;

public class RideOrder extends Order {

    public RideOrder(OrderType type, LocalDateTime dateTime, String from, String to) {
        super(type, dateTime, from, to);
    }

}