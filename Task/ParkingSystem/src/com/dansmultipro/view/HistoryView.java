package com.dansmultipro.view;

import com.dansmultipro.listener.OnBackListener;
import com.dansmultipro.model.Parking;
import com.dansmultipro.service.ParkingService;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class HistoryView {

    private final ParkingService parkingService;
    private final DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    public HistoryView(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    public void show(OnBackListener listener) {
        List<Parking> parkingList = parkingService.getHistory();
        if (parkingList.isEmpty()) {
            System.out.println("No parking history...");
            return;
        }
        System.out.println("======== History view ========");
        for (Parking parking : parkingList) {
            System.out.println(
                    "ID " + parking.getSequence() +
                    " License " + parking.getLicence() +
                    " Type " + parking.getType().getLabel() +
                    " Check in " + parking.getCheckInTime().format(timeFormat) +
                    " Check out " + (parking.getCheckOutTime() == null ? "-" : parking.getCheckOutTime().format(timeFormat)) +
                    " Billed " + parking.getGrandTotal()

            );
        }
        listener.onBackPressed();
    }
}
