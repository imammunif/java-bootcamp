package com.dansmultipro.ams.dto.asset;

public class AssetResponseDto {

    private String id;
    private String name;
    private String categoryName;
    private String statusName;
    private String companyName;
    private String expiredDate;
    private String code;
    private Integer version;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getStatusName() {
        return statusName;
    }

    public String getCompanyName() {
        return companyName;
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
