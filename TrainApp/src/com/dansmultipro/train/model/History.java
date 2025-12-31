package com.dansmultipro.train.model;

import java.util.List;

public class History {

    private Train train;
    private List<SeatRow> seatRows;
    private Double grandTotal;

    public History(Train train, List<SeatRow> seatRows, Double grandTotal) {
        this.train = train;
        this.seatRows = seatRows;
        this.grandTotal = grandTotal;
    }

    public void setGrandTotal(double grandTotal) {
        this.grandTotal = grandTotal;
    }

}
