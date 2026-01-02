package com.dansmultipro.ministore.service.impl;

import com.dansmultipro.ministore.model.History;
import com.dansmultipro.ministore.model.Order;
import com.dansmultipro.ministore.service.HistoryService;

import java.util.ArrayList;
import java.util.List;

public class HistoryServiceImpl implements HistoryService {

    private List<Order> orderList = new ArrayList<>();
    private History history = new History(orderList);

    @Override
    public List<Order> getHistory() {
        return history.getHistory();
    }

    @Override
    public void setOrderHistory(Order newOrder) {
        orderList.add(newOrder);
    }
}
