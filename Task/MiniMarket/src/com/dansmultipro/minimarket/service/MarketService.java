package com.dansmultipro.minimarket.service;

import com.dansmultipro.minimarket.model.Category;
import com.dansmultipro.minimarket.model.Order;
import com.dansmultipro.minimarket.model.Product;

import java.util.List;

public interface MarketService {

    List<Order> getHistories();

    List<Category> getCategories();

    List<Product> getProducts(Category category);

    void setOrderHistory(Order newOrder);

    boolean addCategory(Category newCategory);

    boolean addProduct(Category category, Product newProduct);

    void updateProductStock(Product product, int diffQty);

}
