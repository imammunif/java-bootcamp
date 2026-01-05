package com.dansmultipro;

import com.dansmultipro.service.ParkingService;
import com.dansmultipro.service.impl.ParkingServiceImpl;
import com.dansmultipro.view.HistoryView;
import com.dansmultipro.view.MainView;
import com.dansmultipro.view.ParkingView;

public class App {
    public static void main(String[] args) {

        ParkingService parkingService = new ParkingServiceImpl();

        ParkingView parkingView = new ParkingView(parkingService);
        HistoryView historyView = new HistoryView(parkingService);

        new MainView(parkingView, historyView).show();
    }
}