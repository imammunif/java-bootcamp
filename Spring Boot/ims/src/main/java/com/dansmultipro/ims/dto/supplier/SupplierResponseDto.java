package com.dansmultipro.ims.dto.supplier;

import java.util.UUID;

public class SupplierResponseDto {

    private UUID id;
    private String name;
    private String version;

    public SupplierResponseDto(UUID id, String name, String version) {
        this.id = id;
        this.name = name;
        this.version = version;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

}
