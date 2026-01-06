package com.dansmultipro.minimarket.model;

public class CartItem {

    private Product product;
    private int quantity;
    private Double subtotal;

    public CartItem(Product product, int quantity, Double subtotal) {
        this.product = product;
        this.quantity = quantity;
        this.subtotal = subtotal;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
}