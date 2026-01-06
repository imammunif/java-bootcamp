package com.dansmultipro.minimarket.service.impl;

import com.dansmultipro.minimarket.model.Cart;
import com.dansmultipro.minimarket.model.CartItem;
import com.dansmultipro.minimarket.service.BuyerService;

import java.util.ArrayList;
import java.util.List;

public class BuyerServiceImpl implements BuyerService {

    private List<CartItem> cartItems = new ArrayList<>();
    private Cart cart = new Cart(cartItems);


    @Override
    public List<CartItem> getCartItems() {
        return List.of();
    }

    @Override
    public Double getCartGrandtotal() {
        return 0.0;
    }


    @Override
    public void updateItemQuantity(CartItem item, int diffStock) {

    }

    @Override
    public void updateItemSubtotal(CartItem item) {

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

    }

    @Override
    public Double calculateBil(String voucher) {
        return 0.0;
    }

    @Override
    public Double calculateDiscount(String voucher) {
        return 0.0;
    }
}
