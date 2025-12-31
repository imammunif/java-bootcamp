package com.dansmultipro.train.model;

import java.util.List;

public class Railcar {

    private String name;
    private List<SeatRow> seatRows;

    public Railcar(String name, List<SeatRow> seatRows) {
        this.name = name;
        this.seatRows = seatRows;
    }

    public String getName() {
        return name;
    }

    public List<SeatRow> getSeatRows() {
        return seatRows;
    }

}
