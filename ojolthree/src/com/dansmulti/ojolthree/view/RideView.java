package com.dansmulti.ojolthree.view;

import com.dansmulti.ojolthree.listener.OnBackListener;
import com.dansmulti.ojolthree.model.Driver;
import com.dansmulti.ojolthree.service.RideService;
import com.dansmulti.ojolthree.util.ScannerUtil;

public class RideView {

    private final RideService rideService;

    public RideView(RideService rideService) {
        this.rideService = rideService;
    }

    void show(OnBackListener listener) {
        System.out.println("===== Ride =====");
        String from = ScannerUtil.scanText("From : ");
        String to = ScannerUtil.scanText("To : ");

        Driver driver = rideService.findDriver();
        int totalBill = rideService.calculatePrice(from, to);
        showReceipt(from, to, driver.getName(), driver.getPlatNo(), totalBill);
        listener.onBackPressed();
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