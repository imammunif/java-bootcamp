package com.dansmulti.ojolfour.view;

import com.dansmulti.ojolfour.listener.OnBackListener;
import com.dansmulti.ojolfour.model.Driver;
import com.dansmulti.ojolfour.model.History;
import com.dansmulti.ojolfour.model.order.RideOrder;
import com.dansmulti.ojolfour.service.HistoryService;
import com.dansmulti.ojolfour.service.RideService;
import com.dansmulti.ojolfour.util.ScannerUtil;

import java.time.LocalDateTime;

public class RideView {

    private final RideService rideService;
    private final HistoryService historyService;

    public RideView(RideService rideService, HistoryService historyService) {
        this.rideService = rideService;
        this.historyService = historyService;
    }

    void show(History history, OnBackListener listener) {
        System.out.println("===== Ride =====");
        String from = ScannerUtil.scanText("From : ");
        String to = ScannerUtil.scanText("To : ");

        Driver driver = rideService.findDriver();
        int totalBill = rideService.calculatePrice(from, to);
        RideOrder rideOrder = new RideOrder("Ride", LocalDateTime.now(), from, to);
        historyService.setOrderHistory(history, rideOrder);
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