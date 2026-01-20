package com.dansmultipro.ams.dto.asset;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UpdateAssetRequestDto {

    @NotBlank(message = "Asset name is required")
    @Size(max = 50, message = "Name length exceeds limit, max 50 characters")
    private String name;

    @NotBlank(message = "Asset's status is required")
    private String statusId;

    private String expiredDate;

    @NotBlank(message = "Asset code is required")
    @Size(max = 5, message = "Code length exceeds limit, max 5 characters")
    private String code;

    @NotNull(message = "Version is required")
    private Integer version;

    public String getName() {
        return name;
    }

    public String getStatusId() {
        return statusId;
    }

    public String getExpiredDate() {
        return expiredDate;
    }

    public String getCode() {
        return code;
    }

    public Integer getVersion() {
        return version;
    }

}
