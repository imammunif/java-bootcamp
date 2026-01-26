package com.dansmultipro.ims.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_history_status")
public class HistoryType extends BaseModel {

    @Column(nullable = false, length = 5, unique = true)
    private String code;

    @Column(nullable = false, length = 10)
    private String name;

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
