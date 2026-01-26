package com.dansmultipro.ims.dto.movehistory;

import java.util.UUID;

public class MoveHistoryResponseDto {

    private UUID id;
    private String date;
    private String quantity;
    private String newQuantity;
    private String productName;
    private String historyType;

    public MoveHistoryResponseDto(UUID id, String date, String quantity, String newQuantity, String productName, String historyType) {
        this.id = id;
        this.date = date;
        this.quantity = quantity;
        this.newQuantity = newQuantity;
        this.productName = productName;
        this.historyType = historyType;
    }

    public UUID getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getNewQuantity() {
        return newQuantity;
    }

    public String getProductName() {
        return productName;
    }

    public String getHistoryType() {
        return historyType;
    }

}
