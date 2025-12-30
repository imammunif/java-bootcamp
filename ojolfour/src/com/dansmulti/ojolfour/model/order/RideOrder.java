package com.dansmulti.ojolfour.model.order;

import java.time.LocalDateTime;

public class RideOrder extends Order {

    public RideOrder(String type, LocalDateTime dateTime, String from, String to) {
        super(type, dateTime, from, to);
    }

}
