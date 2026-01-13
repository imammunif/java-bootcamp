package com.dansmultipro.ams.dto.company;

import java.util.UUID;

public class CompanyResponseDto {

    private UUID id;
    private String name;

    public CompanyResponseDto(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

}
