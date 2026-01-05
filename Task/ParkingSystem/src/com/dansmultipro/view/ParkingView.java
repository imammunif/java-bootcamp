package com.dansmultipro.view;

import com.dansmultipro.listener.OnBackListener;
import com.dansmultipro.service.ParkingService;

public class ParkingView {

    private final ParkingService parkingService;

    public ParkingView(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    public void show(OnBackListener listener) {
        System.out.println("Parking view");
    }

    public void showCheckIn() {

    }

    public void showCheckOut() {

    }
}
