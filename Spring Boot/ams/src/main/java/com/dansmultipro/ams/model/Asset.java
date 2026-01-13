package com.dansmultipro.ams.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "t_asset")
public class Asset extends BaseModel {

    @Column(nullable = false, length = 50)
    private String name;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    @Column(length = 36)
    private Company company;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    @Column(length = 36)
    private AssetCategory assetCategory;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    @Column(length = 36)
    private AssetStatus assetStatus;

    @Column
    private Date expiredDate;

    @Column(nullable = false, length = 5, unique = true)
    private String code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public AssetCategory getAssetCategory() {
        return assetCategory;
    }

    public void setAssetCategory(AssetCategory assetCategory) {
        this.assetCategory = assetCategory;
    }

    public AssetStatus getAssetStatus() {
        return assetStatus;
    }

    public void setAssetStatus(AssetStatus assetStatus) {
        this.assetStatus = assetStatus;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
    }

}
