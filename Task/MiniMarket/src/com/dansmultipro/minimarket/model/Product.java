package com.dansmultipro.minimarket.model;

public class Product {

    private String category;
    private String name;
    private Double price;
    private int stock;

    public Product(String category, String name, Double price, int stock) {
        this.category = category;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public String getType() {
        return category;
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