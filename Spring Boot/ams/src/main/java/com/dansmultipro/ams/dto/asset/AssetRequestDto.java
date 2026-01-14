package com.dansmultipro.ams.dto.asset;

public class AssetRequestDto {

    private String name;
    private String companyId;
    private String categoryId;
    private String statusId;
    private String expiredDate;
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

    public String getCode() {
        return code;
    }

    public String getExpiredDate() {
        return expiredDate;
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
