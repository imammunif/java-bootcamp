package com.dansmultipro.ams.dto.location;

public class UpdateLocationRequestDto {

    private String name;
    private String version;

    public UpdateLocationRequestDto(String name, String version) {
        this.name = name;
        this.version = version;
    }

    public String getName() {
        return name;
    }

}
