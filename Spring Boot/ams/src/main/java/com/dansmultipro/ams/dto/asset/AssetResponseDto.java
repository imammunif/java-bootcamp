package com.dansmultipro.ams.dto.asset;

import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

public class AssetResponseDto {

    private UUID id;
    private String name;
    private String categoryName;
    private String statusName;
    private String companyName;
    private LocalDate expiredDate;
    private String code;
    private Integer version;

    public AssetResponseDto(UUID id, String name, String categoryName, String statusName, String companyName, LocalDate expiredDate, String code) {
        this.id = id;
        this.name = name;
        this.categoryName = categoryName;
        this.statusName = statusName;
        this.companyName = companyName;
        this.expiredDate = expiredDate;
        this.code = code;
    }

    public UUID getId() {
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

    public LocalDate getExpiredDate() {
        return expiredDate;
    }

    public String getCode() {
        return code;
    }

    public Integer getVersion() {
        return version;
    }

}
