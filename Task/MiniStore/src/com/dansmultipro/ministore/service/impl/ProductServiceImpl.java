package com.dansmultipro.ministore.service.impl;

import com.dansmultipro.ministore.model.Cart;
import com.dansmultipro.ministore.model.CartItem;
import com.dansmultipro.ministore.model.Product;
import com.dansmultipro.ministore.model.constant.ProductType;
import com.dansmultipro.ministore.service.ProductService;

import java.util.*;

public class ProductServiceImpl implements ProductService {

    private List<CartItem> cartItems = new ArrayList<>();
    private Cart cart = new Cart(cartItems);
    private List<Product> products = Arrays.asList(
            new Product(ProductType.FRUIT, "Nanas", 16000d, 20),
            new Product(ProductType.FRUIT, "Pepaya", 14000d, 40),
            new Product(ProductType.VEGGIE, "Bayam", 10000d, 80),
            new Product(ProductType.VEGGIE, "Brokoli", 8000d, 50),
            new Product(ProductType.NOODLE, "Indomie", 3000d, 500),
            new Product(ProductType.NOODLE, "Mie Sedap", 3000d, 400),
            new Product(ProductType.WATER, "Danone", 3500d, 300),
            new Product(ProductType.WATER, "Le Minerale", 3500d, 400)
    );

    @Override
    public List<Product> getProducts() {
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
    public void updateProductStock(Product product, int diffStock) {
        int newStock = product.getStock() + diffStock;
        product.setStock(newStock);
    }

    @Override
    public void updateItemQuantity(CartItem item, int additionQty) {
        int newQuantity = item.getQuantity() + additionQty;
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
