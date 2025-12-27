package com.dansmulti.ojoltwo.model;

import java.util.List;

public class Cart {
    private List<CartItem> items;
    private double grandTotal;

    public Cart(List<CartItem> items) {
        this.items = items;
    }

    public void addItem(CartItem item) {
        this.items.add(item);
        setGrandTotal();
    }

    public double getGrandTotal() {
        return this.grandTotal;
    }

    public void setGrandTotal() {
        for (int i = 0; i < items.size(); i++) {
            this.grandTotal += items.get(i).getSubtotal();
        }
    }

}

