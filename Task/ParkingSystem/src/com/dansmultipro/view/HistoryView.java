package com.dansmultipro.view;

import com.dansmultipro.listener.OnBackListener;
import com.dansmultipro.service.ParkingService;

import java.time.format.DateTimeFormatter;

public class HistoryView {

    private final ParkingService parkingService;
    DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    public HistoryView(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    public void show(OnBackListener listener) {
        System.out.println("History view");
        listener.onBackPressed();
    }
}
