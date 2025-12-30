package com.dansmulti.ojolfour.view;

import com.dansmulti.ojolfour.model.History;
import com.dansmulti.ojolfour.model.order.Order;
import com.dansmulti.ojolfour.util.ScannerUtil;

import java.util.ArrayList;
import java.util.List;

public class MainView {

    private final RideView rideView;
    private final SendView sendView;
    private final FoodView foodView;
    private final HistoryView historyView;
    private List<Order> orderList = new ArrayList<>();
    private History history = new History(orderList);

    public MainView(RideView rideView, SendView sendView, FoodView foodView, HistoryView historyView) {
        this.rideView = rideView;
        this.sendView = sendView;
        this.foodView = foodView;
        this.historyView = historyView;
    }

    public void show() {
        System.out.println("==== Ojol Pro ====");
        System.out.println("1. Ride");
        System.out.println("2. Send");
        System.out.println("3. Food");
        System.out.println("4. History");

        int chosen = ScannerUtil.scanLimitedOption("\nSelect [1-4] : ", 4);
        if (chosen == 1) {
            rideView.show(history, () -> show());
        } else if (chosen == 2) {
            sendView.show(history, () -> show());
        } else if (chosen == 3) {
            foodView.show(() -> show());
        } else if (chosen == 4) {
            historyView.show(history, () -> show());
        }
    }
}
