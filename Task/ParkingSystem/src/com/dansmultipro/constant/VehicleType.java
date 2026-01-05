package com.dansmultipro.constant;

public enum VehicleType {

    CAR("Car", 5000d),
    MOTORCYCLE("Motorcycle", 1000d);

    private final String label;
    private final Double rate;

    VehicleType(String label, Double rate) {
        this.label = label;
        this.rate = rate;
    }

    public String getLabel() {
        return label;
    }

    public Double getRate() {
        return rate;
    }

}