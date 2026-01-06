package com.dansmultipro.minimarket.service;

import com.dansmultipro.minimarket.model.CartItem;
import com.dansmultipro.minimarket.model.Category;
import com.dansmultipro.minimarket.model.Product;

import java.util.List;

public interface BuyerService {

    List<Category> getCategories();

    List<CartItem> getCartItems();

    Double getCartGrandtotal();

    void updateProductStock(Product product, int diffQty);

    void updateItemQuantity(CartItem item, int diffStock);

    void updateItemSubtotal(CartItem item);

    void addOrUpdateCartItem(CartItem newItem);

    void updateCartGrandtotal();

    Double calculateBil(String voucher);

}
