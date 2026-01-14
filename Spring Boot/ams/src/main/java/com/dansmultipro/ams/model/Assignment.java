package com.dansmultipro.ams.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "t_assignment")
public class Assignment extends BaseModel {

    @Column(nullable = false, length = 20, unique = true)
    private String code;

    @Column(nullable = false)
    private LocalDate assignDate;

    @ManyToOne
    @JoinColumn(name = "target_location_id")
    private Location location;

    @ManyToOne
    @JoinColumn(name = "target_asset_id")
    private Asset asset;

    @ManyToOne
    @JoinColumn(name = "target_employee_id")
    private Employee employee;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDate getAssignDate() {
        return assignDate;
    }

    public void setAssignDate(LocalDate assignDate) {
        this.assignDate = assignDate;
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
