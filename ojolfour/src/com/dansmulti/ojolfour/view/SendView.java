package com.dansmulti.ojolfour.view;

import com.dansmulti.ojolfour.listener.OnBackListener;
import com.dansmulti.ojolfour.model.Driver;
import com.dansmulti.ojolfour.model.History;
import com.dansmulti.ojolfour.model.order.SendOrder;
import com.dansmulti.ojolfour.service.HistoryService;
import com.dansmulti.ojolfour.service.SendService;
import com.dansmulti.ojolfour.util.ScannerUtil;

import java.time.LocalDateTime;
import java.util.List;

public class SendView {

    private final SendService sendService;
    private final HistoryService historyService;

    public SendView(SendService sendService, HistoryService historyService) {
        this.sendService = sendService;
        this.historyService = historyService;
    }

    void show(History history, OnBackListener listener) {
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
        String haveDiscount = ScannerUtil.scanText("\nDo you have voucher? [y/n] : ");
        String voucher = "";
        if ("y".equalsIgnoreCase(haveDiscount)) {
            voucher = ScannerUtil.scanText("Enter voucher : ");
        }
        Double totalBill = sendService.calculateBill(from, to, categories.get(indexCategory), weight, voucher);

        SendOrder sendOrder = new SendOrder("Send", LocalDateTime.now(), from, to);
        historyService.setOrderHistory(history, sendOrder);

        showReceipt(categories.get(indexCategory), from, to, driver.getName(), driver.getPlatNo(), totalBill);
        listener.onBackPressed();
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
