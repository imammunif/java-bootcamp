package com.dansmultipro.minimarket.service;

import com.dansmultipro.minimarket.model.Category;
import com.dansmultipro.minimarket.model.Order;

import java.util.List;

public interface MarketService {

    List<Order> getHistory();

    List<Category> getCatalogList();

    void setOrderHistory(Order newOrder);

    void setCatalogList(Category newCategory);
}
