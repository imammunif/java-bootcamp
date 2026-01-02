package com.dansmultipro.ministore.model;

import com.dansmultipro.ministore.model.constant.ProductType;

public class Product {

    private ProductType type;
    private String name;
    private Double price;
    private int stock;

    public Product(ProductType type, String name, Double price, int stock) {
        this.type = type;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public ProductType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

}
