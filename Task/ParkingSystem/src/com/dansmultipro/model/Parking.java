package com.dansmultipro.model;

import com.dansmultipro.constant.VehicleType;

import java.time.LocalDateTime;

public class Parking {

    private String sequence;
    private VehicleType type;
    private String licence;
    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;
    private Double grandTotal;

    public Parking(String sequence, VehicleType type, String licence, LocalDateTime checkInTime, LocalDateTime checkOutTime, Double grandTotal) {
        this.sequence = sequence;
        this.type = type;
        this.licence = licence;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
        this.grandTotal = grandTotal;
    }

    public String getSequence() {
        return sequence;
    }

    public VehicleType getType() {
        return type;
    }

    public String getLicence() {
        return licence;
    }

    public LocalDateTime getCheckInTime() {
        return checkInTime;
    }

    public Double getGrandTotal() {
        return grandTotal;
    }

    public LocalDateTime getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(LocalDateTime checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

    public void setGrandTotal(Double grandTotal) {
        this.grandTotal = grandTotal;
    }

}
