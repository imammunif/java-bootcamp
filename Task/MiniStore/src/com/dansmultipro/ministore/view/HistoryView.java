package com.dansmultipro.ministore.view;

import com.dansmultipro.ministore.listener.OnBackListener;
import com.dansmultipro.ministore.model.Order;
import com.dansmultipro.ministore.service.HistoryService;

import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
        orderList.stream()
                .sorted(Comparator.comparing(Order::getDateTime).reversed())
                .collect(Collectors.toList())
                .forEach(order -> System.out.println("Order ID: " + order.getSequence() + " Date: " + order.getDateTime().format(timeFormat) + " Total: " + order.getGrandTotal()));
    }

}
