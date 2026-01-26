package com.dansmultipro.ims.dto.supplier;

import java.util.UUID;

public class SupplierResponseDto {

    private UUID id;
    private String name;

    public SupplierResponseDto(UUID id, String name) {
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
