package com.dansmulti.ojolfour.service;

import com.dansmulti.ojolfour.model.History;
import com.dansmulti.ojolfour.model.order.Order;

import java.util.List;

public interface HistoryService {

    List<Order> getHistory(History history);

    void setOrderHistory(History history, Order order);

}