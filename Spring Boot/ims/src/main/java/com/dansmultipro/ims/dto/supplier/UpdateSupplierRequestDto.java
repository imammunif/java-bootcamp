package com.dansmultipro.ims.dto.supplier;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UpdateSupplierRequestDto {

    @NotBlank(message = "Code is required")
    @Size(max = 10, message = "Code length exceeds limit, max 10 characters")
    private String code;

    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name length exceeds limit, max 100 characters")
    private String name;

    @NotBlank(message = "Address is required")
    @Size(max = 250, message = "Address length exceeds limit, max 250 characters")
    private String address;

    @NotBlank(message = "Phone is required")
    @Size(max = 20, message = "Phone length exceeds limit, max 20 characters")
    private String phone;

    @NotNull(message = "Version is required")
    private Integer version;

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

    public Integer getVersion() {
        return version;
    }

}
