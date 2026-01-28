package com.dansmultipro.ims.dto.productcategory;

import java.util.UUID;

public class ProductCategoryResponseDto {

    private UUID id;
    private String code;
    private String name;
    private String version;

    public ProductCategoryResponseDto(UUID id, String code, String name, String version) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.version = version;
    }

    public UUID getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

}
