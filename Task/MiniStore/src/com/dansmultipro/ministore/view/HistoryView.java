package com.dansmultipro.ministore.view;

import com.dansmultipro.ministore.listener.OnBackListener;
import com.dansmultipro.ministore.model.Order;
import com.dansmultipro.ministore.service.HistoryService;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class HistoryView {

    private final HistoryService historyService;
    DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    public HistoryView(HistoryService historyService) {
        this.historyService = historyService;
    }

    public void show(OnBackListener listener) {
        System.out.println("""
                ---- Order History ----
                """);
        List<Order> orderList = historyService.getHistory();
        for (Order order : orderList) {
            System.out.println("Order: " + order.getSequence() + " Grand total: " + order.getGrandTotal());
        }
        listener.onBackPressed();
    }
}
