package com.dansmultipro.tms.dto;

public class ErrorResponseDto<T> {

    private final T message;

    public ErrorResponseDto(T message) {
        this.message = message;
    }

    public T getMessage() {
        return message;
    }

}
