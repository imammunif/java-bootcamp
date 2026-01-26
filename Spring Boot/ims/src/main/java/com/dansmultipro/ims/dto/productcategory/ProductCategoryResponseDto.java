package com.dansmultipro.ims.dto.productcategory;

import java.util.UUID;

public class ProductCategoryResponseDto {

    private UUID id;
    private String name;

    public ProductCategoryResponseDto(UUID id, String name) {
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
