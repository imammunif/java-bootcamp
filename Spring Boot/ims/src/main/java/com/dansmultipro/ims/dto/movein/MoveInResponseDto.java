package com.dansmultipro.ims.dto.movein;

import java.util.UUID;

public class MoveInResponseDto {

    private UUID id;
    private String code;
    private String date;
    private String supplierName;

    public MoveInResponseDto(UUID id, String code, String date, String supplierName) {
        this.id = id;
        this.code = code;
        this.date = date;
        this.supplierName = supplierName;
    }

    public UUID getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getDate() {
        return date;
    }

    public String getSupplierName() {
        return supplierName;
    }

}
