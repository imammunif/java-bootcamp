package com.dansmulti.ojoltwo.view;

import com.dansmulti.ojoltwo.model.Driver;
import com.dansmulti.ojoltwo.service.RideService;

import java.util.Scanner;

public class RideView {

    private final RideService rideService;

    public RideView(RideService rideService) {
        this.rideService = rideService;
    }

    void show() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Ride ====");

        System.out.print("From : ");
        String from = scanner.nextLine();

        System.out.print("To : ");
        String to = scanner.nextLine();

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
