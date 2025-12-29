package com.dansmulti.ojoltwo.model;

import java.util.List;

public class Cart {
    private List<CartItem> items;
    private double grandTotal;

    public Cart(List<CartItem> items) {
        this.items = items;
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

