package com.dansmulti.ojolone.view;

import com.dansmulti.ojolone.model.Driver;
import com.dansmulti.ojolone.service.SendService;

import java.awt.*;
import java.util.Scanner;

public class SendView {

    private final SendService sendService;

    public SendView(SendService sendService) {
        this.sendService = sendService;
    }

    void show() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Send ====");

        String[] categories = sendService.getCategories();
        System.out.println("Available category");
        for (int i = 0; i < categories.length; i++) {
            System.out.println((i+1) + ". " + categories[i]);
        }
        System.out.print("Category : ");
        int ixCategory = scanner.nextInt()-1;
        String category = categories[ixCategory];
        System.out.println("category selected (" + category + ")");

        System.out.print("Weight : ");
        double weight = scanner.nextDouble();

        System.out.print("From : ");
        scanner.nextLine();
        String from = scanner.nextLine();

        System.out.print("To : ");
        String to = scanner.nextLine();

        Driver driver = sendService.findDriver();
        double price = sendService.calculatePrice(from, to, category, weight);

        System.out.println("=== Detail ====");
        System.out.println("From : " + from);
        System.out.println("To : " + to);
        System.out.println("Driver Name : " + driver.getName());
        System.out.println("Driver Plat No : " + driver.getPlatNo());
        System.out.println("Total Price : " + price);
        System.out.println("=== Thanks ===");
    }
}
