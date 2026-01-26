package com.dansmultipro.ims.dto.product;

import java.util.UUID;

public class CreateProductResponseDto {

    private UUID id;
    private String name;

    public CreateProductResponseDto(UUID id, String name) {
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
