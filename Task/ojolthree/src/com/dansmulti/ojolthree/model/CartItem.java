package com.dansmulti.ojolthree.model;

public class CartItem {
    private Menu menu;
    private int quantity;
    private double subtotal;

    public CartItem(Menu menu, int quantity, double subtotal) {
        this.menu = menu;
        this.quantity = quantity;
        this.subtotal = subtotal;
    }

    public Menu getMenu() {
        return menu;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

}
