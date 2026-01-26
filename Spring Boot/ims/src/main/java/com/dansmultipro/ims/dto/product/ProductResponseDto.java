package com.dansmultipro.ims.dto.product;

import java.util.UUID;

public class ProductResponseDto {

    private UUID id;
    private String name;
    private String quantity;

    public ProductResponseDto(UUID id, String name, String quantity) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getQuantity() {
        return quantity;
    }

}
