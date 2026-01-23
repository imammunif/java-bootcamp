package com.dansmultipro.ims.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "t_move_in")
public class MoveIn extends BaseModel {

    @Column(nullable = false, length = 20, unique = true)
    private String code;

    @Column(nullable = false)
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "supplier_id", nullable = false)
    private Supplier supplier;

    public String getCode() {
        return code;
    }

    public LocalDate getDate() {
        return date;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

}
