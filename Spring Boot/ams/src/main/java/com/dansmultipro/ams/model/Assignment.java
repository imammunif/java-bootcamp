package com.dansmultipro.ams.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "t_assignment")
public class Assignment extends BaseModel {

    @Column(nullable = false, length = 20, unique = true)
    private String code;

    @Column(nullable = false)
    private Date expiredDate;

    @ManyToOne
    @JoinColumn(name = "target_location_id")
    @Column(length = 36)
    private Location location;

    @ManyToOne
    @JoinColumn(name = "target_asset_id")
    @Column(length = 36)
    private Asset asset;

    @ManyToOne
    @JoinColumn(name = "target_employee_id")
    @Column(length = 36)
    private Employee employee;

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

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

}
