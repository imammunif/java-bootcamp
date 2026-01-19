package com.dansmultipro.ams.dto.asset;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UpdateAssetRequestDto {

    @NotBlank(message = "Asset name is required")
    @Size(max = 50, message = "Name length exceeds limit")
    private String name;

    @NotBlank(message = "Asset status id is required")
    private String statusId;

    @Size(max = 10, message = "Expired date length exceeds limit")
    private String expiredDate;

    public String getStatusId() {
        return statusId;
    }

    public String getExpiredDate() {
        return expiredDate;
    }

    public String getName() {
        return name;
    }

}
