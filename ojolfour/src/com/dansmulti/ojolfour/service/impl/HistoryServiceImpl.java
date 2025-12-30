package com.dansmulti.ojolfour.service.impl;

import com.dansmulti.ojolfour.model.History;
import com.dansmulti.ojolfour.model.order.Order;
import com.dansmulti.ojolfour.service.HistoryService;

import java.util.List;

public class HistoryServiceImpl implements HistoryService {
    @Override
    public List<Order> getHistory(History history) {
        return history.getHistory();
    }

    @Override
    public void setOrderHistory(History history, Order order) {
        List<Order> historyList = history.getHistory();
        historyList.add(order);
        history.setHistory(historyList);
    }
}
