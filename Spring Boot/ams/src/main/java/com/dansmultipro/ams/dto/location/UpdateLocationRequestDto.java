package com.dansmultipro.ams.dto.location;

public class UpdateLocationRequestDto {

    private String name;

    public UpdateLocationRequestDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
