package com.dansmulti.ojolone.view;

import java.util.Scanner;

public class MainView {

    private final RideView rideView;
    private final SendView sendView;

    public MainView(RideView rideView, SendView sendView) {
        this.rideView = rideView;
        this.sendView = sendView;
    }

    public void show() {
        System.out.println("=== Ojol Pro ====");
        System.out.println("1. Ride");
        System.out.println("2. Send");
        System.out.println("3. Food");
        System.out.print("Pilih [1-3] : ");

        Scanner scanner = new Scanner(System.in);
        int chosen = scanner.nextInt();
        if (chosen == 1) {
            rideView.show();
        } else if (chosen == 2) {
            sendView.show();
        } else if (chosen == 3) {

        }

        show();
    }
}
