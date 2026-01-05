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
                ======= Parking System =======
                [1] Check In
                [2] Check Out
                [3] Show Report
                [4] Exit App""");
        int chosen = ScannerUtil.scanLimitedOption("Select an option [1-4] : ", 4);
        if (chosen == 1) {
            parkingView.showCheckIn(() -> show());
        } else if (chosen == 2) {
            parkingView.showCheckOut(() -> show());
        } else if (chosen == 3) {
            historyView.show(() -> show());
        } else if (chosen == 4) {
            return;
        }
        show();
    }
}
