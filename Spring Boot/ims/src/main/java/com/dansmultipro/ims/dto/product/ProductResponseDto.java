package com.dansmultipro.ims.dto.product;

import java.util.UUID;

public class ProductResponseDto {

    private UUID id;
    private String name;
    private String quantity;
    private String version;

    public ProductResponseDto(UUID id, String name, String quantity, String version) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.version = version;
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

    public String getVersion() {
        return version;
    }

}
