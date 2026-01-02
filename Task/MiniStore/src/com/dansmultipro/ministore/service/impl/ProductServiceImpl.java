package com.dansmultipro.ministore.service.impl;

import com.dansmultipro.ministore.model.Cart;
import com.dansmultipro.ministore.model.CartItem;
import com.dansmultipro.ministore.model.Product;
import com.dansmultipro.ministore.model.constant.ProductType;
import com.dansmultipro.ministore.model.products.FruitsProduct;
import com.dansmultipro.ministore.model.products.NoodlesProduct;
import com.dansmultipro.ministore.model.products.VeggiesProduct;
import com.dansmultipro.ministore.model.products.WaterProduct;
import com.dansmultipro.ministore.service.ProductService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductServiceImpl implements ProductService {

    private List<CartItem> cartItems = new ArrayList<>();
    private Cart cart = new Cart(cartItems);

    @Override
    public List<Product> getProducts() {
        List<Product> products = Arrays.asList(
                new FruitsProduct(ProductType.FRUIT, "Nanas", 16000, 20),
                new FruitsProduct(ProductType.FRUIT, "Pepaya", 14000, 40),
                new VeggiesProduct(ProductType.VEGGIE, "Bayam", 10000, 80),
                new VeggiesProduct(ProductType.VEGGIE, "Brokoli", 8000, 50),
                new NoodlesProduct(ProductType.NOODLE, "Indomie", 3000, 500),
                new NoodlesProduct(ProductType.NOODLE, "Mie Sedap", 3000, 400),
                new WaterProduct(ProductType.WATER, "Danone", 3500, 300),
                new WaterProduct(ProductType.WATER, "Le Minerale", 3500, 400)
        );
        return products;
    }

    @Override
    public List<CartItem> getCartItems() {
        return cartItems;
    }

    @Override
    public Double getCartGrandtotal() {
        return cart.getGrandTotal();
    }

    @Override
    public void updateItemQuantity(CartItem item, int additionQty) {
        int newQuantity = item.getQuantity() + additionQty;
        item.setQuantity(newQuantity);

    }

    @Override
    public void updateItemSubtotal(CartItem item) {
        double newSubtotal = item.getQuantity() * item.getProduct().getPrice();
        item.setSubtotal(newSubtotal);
    }

    @Override
    public void addOrUpdateCartItem(CartItem newItem) {
        for (CartItem item : cartItems) {
            if (item.getProduct().equals(newItem.getProduct())) {
                updateItemQuantity(item, newItem.getQuantity());
                return;
            }
        }
        cartItems.add(newItem);
        cart.setItems(cartItems);
    }

}
