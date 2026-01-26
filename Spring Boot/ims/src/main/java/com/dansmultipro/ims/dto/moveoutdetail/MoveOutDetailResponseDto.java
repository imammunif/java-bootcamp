package com.dansmultipro.ims.dto.moveoutdetail;

import java.util.UUID;

public class MoveOutDetailResponseDto {

    private UUID id;
    private String quantity;
    private String productName;
    private String moveOutCode;

    public MoveOutDetailResponseDto(UUID id, String quantity, String productName, String moveOutCode) {
        this.id = id;
        this.quantity = quantity;
        this.productName = productName;
        this.moveOutCode = moveOutCode;
    }

    public UUID getId() {
        return id;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getProductName() {
        return productName;
    }

    public String getMoveOutCode() {
        return moveOutCode;
    }

}