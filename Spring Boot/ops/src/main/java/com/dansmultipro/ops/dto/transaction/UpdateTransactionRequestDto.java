package com.dansmultipro.ops.dto.transaction;

import jakarta.validation.constraints.NotNull;

public class UpdateTransactionRequestDto {

    @NotNull(message = "Version is required")
    private Integer version;

    public Integer getVersion() {
        return version;
    }

}
