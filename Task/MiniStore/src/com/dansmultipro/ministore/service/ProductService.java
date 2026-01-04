package com.dansmultipro.ministore.service;

import com.dansmultipro.ministore.model.CartItem;
import com.dansmultipro.ministore.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getProducts();

    List<CartItem> getCartItems();

    Double getCartGrandtotal();

    void updateProductStock(Product product, int diffQty);

    void updateItemQuantity(CartItem item, int diffStock);

    void updateItemSubtotal(CartItem item);

    void addOrUpdateCartItem(CartItem newItem);

    void updateCartGrandtotal();

    Double calculateBil(String voucher);

    Double calculateDiscount(String voucher);
}
