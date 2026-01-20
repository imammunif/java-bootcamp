package com.dansmultipro.ams.dto.asset;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AssetRequestDto {

    @NotBlank(message = "Asset name is required")
    @Size(max = 50, message = "Name length exceeds limit, max 50 characters")
    private String name;

    @NotBlank(message = "Asset's company is required")
    private String companyId;

    @NotBlank(message = "Asset's category is required")
    private String categoryId;

    @NotBlank(message = "Asset's status is required")
    private String statusId;

    private String expiredDate;

    @NotBlank(message = "Asset code is required")
    @Size(max = 5, message = "Code length exceeds limit, max 5 characters")
    private String code;

    public String getName() {
        return name;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public String getCompanyId() {
        return companyId;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public void setExpiredDate(String expiredDate) {
        this.expiredDate = expiredDate;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
