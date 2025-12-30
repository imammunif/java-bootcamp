package com.dansmulti.ojolfour.model.order;

import java.time.LocalDateTime;

public class RideOrder extends Order {

    public RideOrder(LocalDateTime dateTime, String from, String to) {
        super(dateTime, from, to);
    }

}
