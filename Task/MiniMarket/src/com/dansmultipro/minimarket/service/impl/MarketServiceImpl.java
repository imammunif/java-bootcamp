package com.dansmultipro.minimarket.service.impl;

import com.dansmultipro.minimarket.model.Category;
import com.dansmultipro.minimarket.model.Order;
import com.dansmultipro.minimarket.model.Product;
import com.dansmultipro.minimarket.service.MarketService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MarketServiceImpl implements MarketService {

    // String sequence, LocalDateTime dateTime, Double grandTotal
    private List<Order> orderList = new ArrayList<>();
    private List<Category> categories = new ArrayList<>();
    private List<Order> histories = new ArrayList<>();

    @Override
    public List<Order> getHistories() {
        return histories;
    }

    @Override
    public List<Category> getCategories() {
        return categories;
    }

    @Override
    public List<Product> getProducts(Category category) {
        return category.getProducts();
    }

    @Override
    public void setOrderHistory(Order newOrder) {
        orderList.add(newOrder);
    }

    @Override
    public boolean addCategory(Category newCategory) {
        for (Category category : categories) {
            if (category.getName().trim().equalsIgnoreCase(newCategory.getName().trim())) {
                return false;
            }
        }
        categories.add(newCategory);
        return true;
    }

    @Override
    public boolean addProduct(Category category, Product newProduct) {
        List<Product> products = category.getProducts();
        for (Product product : products) {
            if (product.getName().trim().equalsIgnoreCase(newProduct.getName().trim())) {
                return false;
            }
        }
        products.add(newProduct);
        return true;
    }

    @Override
    public void updateProductStock(Product product, int diffQty) {
        int newStock = product.getStock() + diffQty;
        product.setStock(newStock);

    }
}