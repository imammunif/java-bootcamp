package com.dansmultipro.ims.dto.moveindetail;

import java.util.UUID;

public class MoveInDetailResponseDto {

    private UUID id;
    private String quantity;
    private String productName;
    private String moveInCode;

    public MoveInDetailResponseDto(UUID id, String quantity, String productName, String moveInCode) {
        this.id = id;
        this.quantity = quantity;
        this.productName = productName;
        this.moveInCode = moveInCode;
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

    public String getMoveInCode() {
        return moveInCode;
    }

}
