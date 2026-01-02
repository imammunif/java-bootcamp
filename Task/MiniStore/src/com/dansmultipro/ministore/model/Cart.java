package com.dansmultipro.ministore.model;

import java.util.List;

public class Cart {

    private List<CartItem> items;
    private Double grandTotal;

    public Cart(List<CartItem> items) {
        this.items = items;
    }

    public List<CartItem> getItems() {
        return this.items;
    }

    public Double getGrandTotal() {
        return this.grandTotal;
    }

    public void setItems(List<CartItem> cartItems) {
        this.items = cartItems;
    }

    public void setGrandTotal(Double grandTotal) {
        this.grandTotal = grandTotal;
    }

}
