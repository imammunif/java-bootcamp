package com.dansmultipro.ams.dto;

public class UpdateResponseDto {

    private Integer version;
    private String message;

    public UpdateResponseDto(Integer version, String message) {
        this.version = version;
        this.message = message;
    }

}
