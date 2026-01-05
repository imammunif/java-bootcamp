package com.dansmultipro.model;

import com.dansmultipro.constant.VehicleType;

import java.time.LocalDateTime;

public class Parking {

    private String sequence;
    private VehicleType type;
    private String licence;
    private Boolean isCheckIn;
    private Boolean isCheckOut;
    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;
    private Double grandTotal;

    public Parking(String sequence, VehicleType type, String licence, Boolean isCheckIn, Boolean isCheckOut, LocalDateTime checkInTime, LocalDateTime checkOutTime, Double grandTotal) {
        this.sequence = sequence;
        this.type = type;
        this.licence = licence;
        this.isCheckIn = isCheckIn;
        this.isCheckOut = isCheckOut;
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

    public Boolean isCheckIn() {
        return isCheckIn;
    }

    public Boolean isCheckOut() {
        return isCheckOut;
    }

    public LocalDateTime getCheckInTime() {
        return checkInTime;
    }

    public LocalDateTime getCheckOutTime() {
        return checkOutTime;
    }

    public Double getGrandTotal() {
        return grandTotal;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public void setLicence(String licence) {
        this.licence = licence;
    }

    public void setCheckIn(Boolean isCheckIn) {
        this.isCheckIn = isCheckIn;
    }

    public void setCheckOut(Boolean isCheckOut) {
        this.isCheckOut = isCheckOut;
    }

    public void setGrandTotal(Double grandTotal) {
        this.grandTotal = grandTotal;
    }

}
