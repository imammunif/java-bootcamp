package com.dansmulti.ojolfour.view;

import com.dansmulti.ojolfour.listener.OnBackListener;
import com.dansmulti.ojolfour.model.History;
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
            System.out.println("\n===== Your have no history. Order a service first! =====");
        } else {
            printRideHistory(orderList);
            printSendHistory(orderList);
            printFoodHistory(orderList);
        }
        listener.onBackPressed();
    }

    private void printRideHistory(List<Order> orderList) {
        System.out.println("\n===== Ride service =====");
        boolean found = false;
        for (Order order : orderList) {
            if ("ride".equalsIgnoreCase(order.getType())) {
                System.out.println("Date time : " + order.getDateTime().format(timeFormat) + " | From: " + order.getFrom() + " | To: " + order.getTo());
            }
            found = true;
        }
        if (!found) {
            System.out.println("\n===== No ride service history! =====");
        }
    }

    private void printSendHistory(List<Order> orderList) {
        System.out.println("\n===== Send service =====");
        boolean found = false;
        for (Order order : orderList) {
            if ("send".equalsIgnoreCase(order.getType())) {
                System.out.println("Date time : " + order.getDateTime().format(timeFormat) + " | From: " + order.getFrom() + " | To: " + order.getTo());
            }
            found = true;
        }
        if (!found) {
            System.out.println("\n===== No send service history! =====");
        }
    }

    private void printFoodHistory(List<Order> orderList) {
        System.out.println("\n===== Food service =====");
        boolean found = false;
        for (Order order : orderList) {
            if ("food".equalsIgnoreCase(order.getType())) {
                System.out.println("Date time : " + order.getDateTime().format(timeFormat) + " | From: " + order.getFrom() + " | To: " + order.getTo());
            }
            found = true;
        }
        if (!found) {
            System.out.println("\n===== No food service history! =====");
        }
    }

}
