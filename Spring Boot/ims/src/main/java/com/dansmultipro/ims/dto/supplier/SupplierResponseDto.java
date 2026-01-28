package com.dansmultipro.ims.dto.supplier;

import java.util.UUID;

public class SupplierResponseDto {

    private UUID id;
    private String code;
    private String name;
    private String address;
    private String phone;
    private String version;

    public SupplierResponseDto(UUID id, String code, String name, String address, String phone, String version) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.version = version;
    }

    public UUID getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getVersion() {
        return version;
    }

}
