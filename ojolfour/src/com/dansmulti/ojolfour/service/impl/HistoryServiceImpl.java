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
}
