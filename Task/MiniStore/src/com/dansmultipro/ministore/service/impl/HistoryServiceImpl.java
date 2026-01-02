package com.dansmultipro.ministore.service.impl;

import com.dansmultipro.ministore.model.History;
import com.dansmultipro.ministore.model.Order;
import com.dansmultipro.ministore.model.constant.ProductType;
import com.dansmultipro.ministore.model.products.FruitsProduct;
import com.dansmultipro.ministore.service.HistoryService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HistoryServiceImpl implements HistoryService {

    private List<Order> orderList = new ArrayList<>();
    private History history = new History(orderList);

    private void addDummy() {
        // String sequence, List<Product > products, LocalDateTime dateTime, Double grandTotal
        orderList.add(new Order("TRX123", Arrays.asList(new FruitsProduct(ProductType.FRUIT, "Nanas", 16000, 20), new FruitsProduct(ProductType.FRUIT, "Nanas", 16000, 20)), LocalDateTime.now(), 89000.0));
        orderList.add(new Order("TRX123", Arrays.asList(new FruitsProduct(ProductType.FRUIT, "Nanas", 16000, 20), new FruitsProduct(ProductType.FRUIT, "Nanas", 16000, 20)), LocalDateTime.now(), 89000.0));
    }

    @Override
    public List<Order> getHistory() {
        addDummy();
        return history.getHistory();
    }
}
