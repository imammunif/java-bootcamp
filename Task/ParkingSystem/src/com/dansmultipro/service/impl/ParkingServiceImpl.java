package com.dansmultipro.service.impl;

import com.dansmultipro.model.Parking;
import com.dansmultipro.service.ParkingService;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ParkingServiceImpl implements ParkingService {

    private List<Parking> parkingList = new ArrayList<>();

    @Override
    public List<Parking> getHistory() {
        return parkingList;
    }

    @Override
    public void setParkingHistory(Parking newParking) {
        parkingList.add(newParking);
    }

    @Override
    public Boolean isExistLicenseCheckout(List<Parking> parkingList, Parking newParking) {
        return parkingList.stream()
                .anyMatch(p -> p.getLicence().trim().equalsIgnoreCase(newParking.getLicence()) && (p.isCheckOut() == false));
    }

    @Override
    public void checkoutParking(Parking newParking) {
        newParking.setCheckOut(true);
        newParking.setCheckOutTime(LocalDateTime.now());
    }

    @Override
    public void calculateBill(Parking parkingToCheckout) {
        Duration duration = Duration.between(parkingToCheckout.getCheckInTime(), parkingToCheckout.getCheckOutTime());
        long durationInMinute = duration.toMinutes();
        long durationInHours = duration.toHours();
        if (durationInMinute % 60 != 0) {
            durationInHours++;
        }
        System.out.println("Bill " + parkingToCheckout.getType().getRate() + " * " + durationInHours);
        parkingToCheckout.setGrandTotal(parkingToCheckout.getType().getRate() * durationInHours);
    }

}
