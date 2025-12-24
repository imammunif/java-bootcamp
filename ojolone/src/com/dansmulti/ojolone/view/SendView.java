package com.dansmulti.ojolone.view;

import com.dansmulti.ojolone.model.Driver;
import com.dansmulti.ojolone.service.SendService;

import java.util.List;
import java.util.Scanner;

public class SendView {

    private final SendService sendService;

    public SendView(SendService sendService) {
        this.sendService = sendService;
    }

    void show() {
        Scanner scanner1 = new Scanner(System.in);
        Scanner scanner2 = new Scanner(System.in);
        System.out.println("=== Send ====");

        List<String> categories = sendService.getCategories();
        System.out.println("Available category");
        for (int i = 0; i < categories.size(); i++) {
            System.out.println((i + 1) + ". " + categories.get(i));
        }
        System.out.print("Category : ");
        int ixCategory = scanner1.nextInt() - 1;
        String category = categories.get(ixCategory);
        System.out.println("category selected (" + category + ")");

        System.out.print("Weight : ");
        double weight = scanner1.nextDouble();

        System.out.print("From : ");
        String from = scanner2.nextLine();

        System.out.print("To : ");
        String to = scanner2.nextLine();

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
