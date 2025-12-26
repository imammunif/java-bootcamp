package com.dansmulti.ojoltwo.view;

import com.dansmulti.ojoltwo.model.Driver;
import com.dansmulti.ojoltwo.service.SendService;
import com.dansmulti.ojoltwo.util.ScannerUtil;

import java.util.List;

public class SendView {

    private final SendService sendService;

    public SendView(SendService sendService) {
        this.sendService = sendService;
    }

    void show() {
        System.out.println("=== Send ====");
        List<String> categories = sendService.getCategories();
        System.out.println("Available category");
        for (int i = 0; i < categories.size(); i++) {
            System.out.println((i + 1) + ". " + categories.get(i));
        }
        int input = ScannerUtil.scanOptionNumber("Select category : ", categories.size());
        int indexCategory = input - 1;
        System.out.println("category selected (" + categories.get(indexCategory) + ")");

        double weight = ScannerUtil.scanDouble("Weight : ");
        String from = ScannerUtil.scanText("From : ");
        String to = ScannerUtil.scanText("To : ");

        Driver driver = sendService.findDriver();
        double price = sendService.calculatePrice(from, to, categories.get(indexCategory), weight);

        System.out.println("=== Detail ====");
        System.out.println("From : " + from);
        System.out.println("To : " + to);
        System.out.println("Driver Name : " + driver.getName());
        System.out.println("Driver Plat No : " + driver.getPlatNo());
        System.out.println("Total Price : " + price);
        System.out.println("=== Thanks ===");
    }
}
