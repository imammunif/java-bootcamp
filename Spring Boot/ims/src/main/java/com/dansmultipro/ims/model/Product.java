package com.dansmultipro.ims.model;

import jakarta.persistence.*;

@Entity
@Table(name = "t_product")
public class Product extends BaseModel {

    @Column(nullable = false, length = 40)
    private String name;

    @Column(nullable = false)
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private ProductCategory category;

    public String getName() {
        return name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

}
