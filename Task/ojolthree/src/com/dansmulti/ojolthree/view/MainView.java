package com.dansmulti.ojolthree.view;

import com.dansmulti.ojolthree.util.ScannerUtil;

public class MainView {

    private final RideView rideView;
    private final SendView sendView;
    private final FoodView foodView;

    public MainView(RideView rideView, SendView sendView, FoodView foodView) {
        this.rideView = rideView;
        this.sendView = sendView;
        this.foodView = foodView;
    }

    public void show() {
        System.out.println("==== Ojol Pro ====");
        System.out.println("1. Ride");
        System.out.println("2. Send");
        System.out.println("3. Food");

        int chosen = ScannerUtil.scanLimitedOption("\nSelect [1-3] : ", 3);
        if (chosen == 1) {
            rideView.show(() -> show());
        } else if (chosen == 2) {
            sendView.show(() -> show());
        } else if (chosen == 3) {
            foodView.show(() -> show());
        }
    }
}
