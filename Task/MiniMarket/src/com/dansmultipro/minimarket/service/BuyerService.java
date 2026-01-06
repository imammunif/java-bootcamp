package com.dansmultipro.minimarket.service;

import com.dansmultipro.minimarket.model.CartItem;

import java.util.List;

public interface BuyerService {

    List<CartItem> getCartItems();

    Double getCartGrandtotal();

    void updateItemQuantity(CartItem item, int diffStock);

    void updateItemSubtotal(CartItem item);

    void addOrUpdateCartItem(CartItem newItem);

    void updateCartGrandtotal();

    Double calculateBil(String voucher);

    Double calculateDiscount(String voucher);
}
