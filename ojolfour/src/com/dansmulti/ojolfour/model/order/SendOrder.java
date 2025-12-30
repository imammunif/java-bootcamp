package com.dansmulti.ojolfour.model.order;

import java.time.LocalDateTime;

public class SendOrder extends Order {

    private double weight;
    private String category;

    public SendOrder(String type, LocalDateTime dateTime, String from, String to) {
        super(type, dateTime, from, to);
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getWeight() {
        return weight;
    }

    public String getCategory() {
        return category;
    }

}
