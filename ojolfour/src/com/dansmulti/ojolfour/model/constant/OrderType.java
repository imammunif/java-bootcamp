package com.dansmulti.ojolfour.model.constant;

public enum OrderType {
    RIDE("Ride"),
    SEND("Send"),
    FOOD("Food");

    private final String label;

    OrderType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

}
