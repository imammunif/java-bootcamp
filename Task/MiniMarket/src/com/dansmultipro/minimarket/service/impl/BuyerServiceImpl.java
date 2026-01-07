package com.dansmultipro.minimarket.service.impl;

import com.dansmultipro.minimarket.model.Cart;
import com.dansmultipro.minimarket.model.CartItem;
import com.dansmultipro.minimarket.service.BuyerService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BuyerServiceImpl implements BuyerService {

    private List<CartItem> cartItems = new ArrayList<>();
    private Cart cart = new Cart();


    @Override
    public List<CartItem> getCartItems() {
        return cartItems;
    }

    @Override
    public Double getCartGrandtotal() {
        return cart.getGrandTotal();
    }


    @Override
    public void updateItemQuantity(CartItem item, int diffStock) {
        int newQuantity = item.getQuantity() + diffStock;
        item.setQuantity(newQuantity);
        updateItemSubtotal(item);
    }

    @Override
    public void updateItemSubtotal(CartItem item) {
        double newSubtotal = item.getQuantity() * item.getProduct().getPrice();
        item.setSubtotal(newSubtotal);
    }

    @Override
    public void addOrUpdateCartItem(CartItem newItem) {
        for (CartItem item : cartItems) {
            if (item.getProduct().getName().equals(newItem.getProduct().getName())) {
                updateItemQuantity(item, newItem.getQuantity());
                updateCartGrandtotal();
                return;
            }
        }
        cartItems.add(newItem);
        cart.setItems(cartItems);
        updateCartGrandtotal();
    }

    @Override
    public void updateCartGrandtotal() {
        Double newGrandtotal = cartItems.stream()
                .map(item -> item.getSubtotal())
                .reduce(0d, (sub1, sub2) -> sub1 + sub2);
        cart.setGrandTotal(newGrandtotal);
    }

    @Override
    public Double calculateBil(String voucher) {
        Double discount = calculateDiscount(voucher);
        Double totalBill = getCartGrandtotal();
        return totalBill - (totalBill * discount / 100.0);
    }

    @Override
    public Double calculateDiscount(String voucher) {
        Map<String, Double> discounts = new HashMap<>();
        discounts.put("TAKASIMURAH12", 12.0);
        discounts.put("KENYANGAJA25", 25.0);

        if (discounts.containsKey(voucher)) {
            return discounts.get(voucher);
        }
        return 0.0;
    }
}
