package com.dansmultipro.train.model;

import java.util.Map;

public class SeatRow {

    private String name;
    private Map<Integer, Boolean> seats;

    public SeatRow(String name, Map<Integer, Boolean> seats) {
        this.name = name;
        this.seats = seats;
    }

    public String getName() {
        return name;
    }

    public Map<Integer, Boolean> getSeats() {
        return seats;
    }

}
