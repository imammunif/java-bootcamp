package com.dansmultipro.tms.dto;

public class DeleteResponseDto {

    private final String message;

    public DeleteResponseDto(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
