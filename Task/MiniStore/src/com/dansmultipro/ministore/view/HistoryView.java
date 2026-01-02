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
        List<Order> orderList = historyService.getHistory();
        System.out.println("--- Showing your order History ---");

        if (orderList.isEmpty()) {
            System.out.println("Oops, you have no order history.");
            System.out.println("Order a product first!");
        } else {
            showOrderHistory(orderList);
        }
        listener.onBackPressed();
    }

    private void showOrderHistory(List<Order> orderList) {
        for (Order order : orderList) {
            System.out.println("Order ID: " + order.getSequence() + "Date: "  + order.getDateTime().format(timeFormat) + " Grand total: " + order.getGrandTotal());
        }
    }

}
