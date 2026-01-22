package com.dansmultipro.tms.dto.product;

import java.util.UUID;

public class ProductResponseDto {

    private UUID id;
    private String name;

    public ProductResponseDto(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
