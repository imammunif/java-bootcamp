package com.dansmulti.ojolthree.view;

import com.dansmulti.ojolthree.model.Driver;
import com.dansmulti.ojolthree.service.SendService;
import com.dansmulti.ojolthree.util.ScannerUtil;

import java.util.List;

public class SendView {

    private final SendService sendService;

    public SendView(SendService sendService) {
        this.sendService = sendService;
    }

    void show() {
        System.out.println("===== Send =====");
        System.out.println("Available category");

        List<String> categories = sendService.getCategories();
        for (int i = 0; i < categories.size(); i++) {
            System.out.println((i + 1) + ". " + categories.get(i));
        }

        int input = ScannerUtil.scanLimitedOption("Select category : ", categories.size());
        int indexCategory = input - 1;
        double weight = ScannerUtil.scanDouble("Weight : ");
        String from = ScannerUtil.scanText("From : ");
        String to = ScannerUtil.scanText("To : ");

        Driver driver = sendService.findDriver();
        double totalBill = sendService.calculatePrice(from, to, categories.get(indexCategory), weight);
        showReceipt(categories.get(indexCategory), from, to, driver.getName(), driver.getPlatNo(), totalBill);
    }

    void showReceipt(String category, String from, String to, String driverName, String licensePlate, double totalBill) {
        System.out.println("===== Detail =====");
        System.out.println("Send using (" + category + ")");
        System.out.println("From : " + from);
        System.out.println("To : " + to);
        System.out.println("Driver Name : " + driverName);
        System.out.println("Driver Plat No : " + licensePlate);
        System.out.println("Total Price : " + totalBill);
        System.out.println("===== Thanks =====");
    }
}
