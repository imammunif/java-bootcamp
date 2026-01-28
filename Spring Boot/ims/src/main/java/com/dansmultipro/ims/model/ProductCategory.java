package com.dansmultipro.ims.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_m_product_category")
public class ProductCategory extends BaseModel {

    @Column(nullable = false, length = 10, unique = true)
    private String code;

    @Column(nullable = false, length = 40)
    private String name;

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

}
