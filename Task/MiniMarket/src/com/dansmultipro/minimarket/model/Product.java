package com.dansmultipro.minimarket.model;

public class Product {

    private String name;
    private Double price;
    private int stock;
    private Category category;

    public Product(String name, Double price, int stock, Category category) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.category = category;
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

    public Category getCategory() {
        return category;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

}