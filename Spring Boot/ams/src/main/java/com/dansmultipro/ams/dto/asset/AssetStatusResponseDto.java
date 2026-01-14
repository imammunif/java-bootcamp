package com.dansmultipro.ams.dto.asset;

import java.util.UUID;

public class AssetStatusResponseDto {

    private UUID id;
    private String name;
    private String code;

    public AssetStatusResponseDto(UUID id, String name, String code) {
        this.id = id;
        this.name = name;
        this.code = code;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }


}
