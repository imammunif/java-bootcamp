package com.dansmultipro.minimarket.model;

public class Voucher {
    private String code;
    private Double discount;

    public Voucher(String code, Double discount) {
        this.code = code;
        this.discount = discount;
    }

    public String getCode() {
        return code;
    }

    public Double getDiscount() {
        return discount;
    }
}
