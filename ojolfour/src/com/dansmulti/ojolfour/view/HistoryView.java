package com.dansmulti.ojolfour.view;

import com.dansmulti.ojolfour.listener.OnBackListener;
import com.dansmulti.ojolfour.model.History;
import com.dansmulti.ojolfour.model.constant.OrderType;
import com.dansmulti.ojolfour.model.order.Order;
import com.dansmulti.ojolfour.service.HistoryService;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class HistoryView {

    private final HistoryService historyService;
    DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    public HistoryView(HistoryService historyService) {
        this.historyService = historyService;
    }

    void show(History history, OnBackListener listener) {
        System.out.println("===== History =====");
        List<Order> orderList = historyService.getHistory(history);
        if (orderList.isEmpty()) {
            System.out.println("Oops, you have no history.");
            System.out.println("Order a service first!");
        } else {
            printOrderByType(orderList, OrderType.RIDE);
            printOrderByType(orderList, OrderType.SEND);
            printOrderByType(orderList, OrderType.FOOD);
        }
        listener.onBackPressed();
    }

    private void printOrderByType(List<Order> orderList, OrderType type) {
        System.out.println("--" + type.getLabel() + " order --");
        boolean found = false;
        for (Order order : orderList) {
            if (order.getType() == type) {
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("No " + type.getLabel() + " order history!");
            return;
        }
        for (Order order : orderList) {
            if (order.getType() == type) {
                System.out.println("Date time : " + order.getDateTime().format(timeFormat) + " | From: " + order.getFrom() + " | To: " + order.getTo());
            }
        }
    }

}
