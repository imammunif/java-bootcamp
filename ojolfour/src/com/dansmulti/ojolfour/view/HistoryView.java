package com.dansmulti.ojolfour.view;

import com.dansmulti.ojolfour.listener.OnBackListener;
import com.dansmulti.ojolfour.model.History;
import com.dansmulti.ojolfour.model.order.Order;
import com.dansmulti.ojolfour.service.HistoryService;

import java.util.List;

public class HistoryView {

    private final HistoryService historyService;

    public HistoryView(HistoryService historyService) {
        this.historyService = historyService;
    }

    void show(History history, OnBackListener listener) {
        System.out.println("===== History =====");
        List<Order> orderList = historyService.getHistory(history);
        if (orderList.isEmpty()) {
            System.out.println("\n===== Your have no history. Order a service first! =====");
        }
        listener.onBackPressed();
    }
}
