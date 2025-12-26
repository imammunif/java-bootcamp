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
        System.out.println("===== Ride =====");
        String from = ScannerUtil.scanText("From : ");
        String to = ScannerUtil.scanText("To : ");

        Driver driver = rideService.findDriver();
        int totalBill = rideService.calculatePrice(from, to);
        showReceipt(from, to, driver.getName(), driver.getPlatNo(), totalBill);
    }

    void showReceipt(String from, String to, String driverName, String licensePlate, int totalBill) {
        System.out.println("===== Detail =====");
        System.out.println("From : " + from);
        System.out.println("To : " + to);
        System.out.println("Driver Name : " + driverName);
        System.out.println("Driver Plat No : " + licensePlate);
        System.out.println("Total Price : " + totalBill);
        System.out.println("===== Thanks =====");
    }
}