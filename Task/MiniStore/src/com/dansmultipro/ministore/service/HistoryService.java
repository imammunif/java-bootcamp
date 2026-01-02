package com.dansmultipro.ministore.service;

import com.dansmultipro.ministore.model.Order;

import java.util.List;

public interface HistoryService {

    List<Order> getHistory();

    void setOrderHistory(Order newOrder);
}
