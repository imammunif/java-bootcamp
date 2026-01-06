package com.dansmultipro.minimarket.service.impl;

import com.dansmultipro.minimarket.model.Category;
import com.dansmultipro.minimarket.model.Order;
import com.dansmultipro.minimarket.model.Product;
import com.dansmultipro.minimarket.service.MarketService;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class MarketServiceImpl implements MarketService {

    // String sequence, LocalDateTime dateTime, Double grandTotal
    private List<Order> orderList = Arrays.asList(
            new Order("TRX1109", LocalDateTime.now().minusMinutes(156), 56000d),
            new Order("TRX1109", LocalDateTime.now().minusMinutes(156), 56000d),
            new Order("TRX1109", LocalDateTime.now().minusMinutes(156), 56000d),
            new Order("TRX1109", LocalDateTime.now().minusMinutes(156), 56000d),
            new Order("TRX1109", LocalDateTime.now().minusMinutes(156), 56000d),
            new Order("TRX1109", LocalDateTime.now().minusMinutes(156), 56000d)
    );

    private List<Category> catalogList = Arrays.asList(
            // dummy data
            new Category("Fruit",
                    Arrays.asList(
                            new Product("Fruit", "Nanas", 16000d, 20),
                            new Product("Fruit", "Mangga", 16000d, 20),
                            new Product("Fruit", "Pepaya", 14000d, 40)
                    )),
            new Category("Instant Noodle",
                    Arrays.asList(
                            new Product("Instant Noodle", "Indomie", 3000d, 500),
                            new Product("Instant Noodle", "Mie Sedap", 3000d, 400),
                            new Product("Instant Noodle", "Pop Mie", 10000d, 400),
                            new Product("Instant Noodle", "Pop Mie Mini", 8000d, 400)
                    )),
            new Category("Veggies",
                    Arrays.asList(
                            new Product("Veggies", "Bayam", 10000d, 80),
                            new Product("Veggies", "Brokoli", 8000d, 50)
                    )),
            new Category("Mineral Water",
                    Arrays.asList(
                            new Product("Mineral Water", "Danone", 3500d, 300),
                            new Product("Mineral Water", "Le Minerale", 3500d, 400)
                    ))
    );

    @Override
    public List<Order> getHistory() {
        return List.of();
    }

    @Override
    public List<Category> getCatalogList() {
        return catalogList;
    }

    @Override
    public void setOrderHistory(Order newOrder) {

    }

    @Override
    public void setCatalogList(Category newCategory) {

    }
}