package com.dansmultipro.ams.dto.role;

import java.util.UUID;

public class RoleResponseDto {

    private UUID id;
    private String name;
    private String code;

    public RoleResponseDto(UUID id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }

}
