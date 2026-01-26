package com.dansmultipro.ims.dto.historytype;

import java.util.UUID;

public class HistoryTypeResponseDto {

    private UUID id;
    private String code;
    private String name;

    public HistoryTypeResponseDto(UUID id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
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

}
