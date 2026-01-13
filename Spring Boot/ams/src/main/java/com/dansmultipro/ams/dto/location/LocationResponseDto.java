package com.dansmultipro.ams.dto.location;

import java.util.UUID;

public class LocationResponseDto {

    private UUID id;
    private String name;

    public LocationResponseDto(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

}
