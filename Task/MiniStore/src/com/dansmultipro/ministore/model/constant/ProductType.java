package com.dansmultipro.ministore.model.constant;

public enum ProductType {

    FRUIT("Fruit"),
    VEGGIE("Veggie"),
    NOODLE("Noodle"),
    WATER("Water");

    private final String label;

    ProductType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

}
