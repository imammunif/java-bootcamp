package com.dansmulti.ojolfour.model.order;

import com.dansmulti.ojolfour.model.constant.OrderType;

import java.time.LocalDateTime;

public class SendOrder extends Order {

    public SendOrder(OrderType type, LocalDateTime dateTime, String from, String to) {
        super(type, dateTime, from, to);
    }

}