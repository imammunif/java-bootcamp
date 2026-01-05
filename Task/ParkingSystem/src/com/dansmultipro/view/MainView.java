package com.dansmultipro.view;

import com.dansmultipro.util.ScannerUtil;

public class MainView {

    private final ParkingView parkingView;
    private final HistoryView historyView;

    public MainView(ParkingView parkingView, HistoryView historyView) {
        this.parkingView = parkingView;
        this.historyView = historyView;
    }

    public void show() {
        System.out.println("""
                ======= Mini Store =======
                [1] Check In
                [2] Check Out
                [3] Show Report
                [0] Exit App""");
        int chosen = ScannerUtil.scanIntegerLimited("Select an option [0-3] : ", 3, "Invalid option");
        if (chosen == 1) {
            parkingView.show(() -> show());
        } else if (chosen == 2) {
            parkingView.show(() -> show());
        } else if (chosen == 3) {
            historyView.show(() -> show());
        } else if (chosen == 0) {
            return;
        }
        show();
    }
}
