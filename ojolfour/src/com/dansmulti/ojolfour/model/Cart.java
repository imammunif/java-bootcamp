package com.dansmulti.ojolfour.model;

import java.util.List;

public class Cart {

    private Restaurant restaurant;
    private List<CartItem> items;
    private double grandTotal;

    public Cart(List<CartItem> items) {
        this.items = items;
    }

    public Restaurant getRestaurant() {
        return this.restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<CartItem> getItems() {
        return this.items;
    }

    public void setItems(List<CartItem> cartItems) {
        this.items = cartItems;
    }

    public double getGrandTotal() {
        return this.grandTotal;
    }

    public void setGrandTotal(double grandTotal) {
        this.grandTotal = grandTotal;
    }

}