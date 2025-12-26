package com.dansmulti.ojoltwo.view;

import com.dansmulti.ojoltwo.model.Driver;
import com.dansmulti.ojoltwo.service.RideService;
import com.dansmulti.ojoltwo.util.ScannerUtil;

public class RideView {

    private final RideService rideService;

    public RideView(RideService rideService) {
        this.rideService = rideService;
    }

    void show() {
        System.out.println("=== Ride ====");
        String from = ScannerUtil.scanText("From : ");
        String to = ScannerUtil.scanText("To : ");
        Driver driver = rideService.findDriver();
        int price = rideService.calculatePrice(from, to);

        System.out.println("=== Detail ====");
        System.out.println("From : " + from);
        System.out.println("To : " + to);
        System.out.println("Driver Name : " + driver.getName());
        System.out.println("Driver Plat No : " + driver.getPlatNo());
        System.out.println("Total Price : " + price);
        System.out.println("=== Thanks ===");
    }
}
