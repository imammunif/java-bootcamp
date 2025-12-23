package com.dansmulti.ojolone.view;

import com.dansmulti.ojolone.model.Driver;
import com.dansmulti.ojolone.model.Restaurant;
import com.dansmulti.ojolone.service.FoodService;

import java.util.Scanner;

public class FoodView {

    private final FoodService foodService;

    public FoodView(FoodService foodService) {
        this.foodService = foodService;
    }

    void show() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Food ====");

        Restaurant[] restaurants = foodService.getRestaurants();

        System.out.println("Available restaurant");
        for (int i = 0; i < restaurants.length; i++) {
            System.out.println((i + 1) + ". " + restaurants[i].getName() + " (" + restaurants[i].getAddress() + ")");
        }

        System.out.print("Select a restaurant : ");
        int idxRestaurant = scanner.nextInt()-1;
        Restaurant restaurant = restaurants[idxRestaurant];
        System.out.println("Menu in " + restaurant.getName() + ":");
        showMenu();

    }

    void showMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Menu ====");
//
//        String[] categories = sendService.getCategories();
//        System.out.println("Available category");
//        for (int i = 0; i < categories.length; i++) {
//            System.out.println((i+1) + ". " + categories[i]);
//        }
//        System.out.print("Category : ");
//        int ixCategory = scanner.nextInt()-1;
//        String category = categories[ixCategory];
//        System.out.println("category selected (" + category + ")");
//
//        System.out.print("Weight : ");
//        double weight = scanner.nextDouble();
//
//        System.out.print("From : ");
//        scanner.nextLine();
//        String from = scanner.nextLine();
//
//        System.out.print("To : ");
//        String to = scanner.nextLine();
//
//        Driver driver = sendService.findDriver();
//        double price = sendService.calculatePrice(from, to, category, weight);
//
//        System.out.println("=== Detail ====");
//        System.out.println("From : " + from);
//        System.out.println("To : " + to);
//        System.out.println("Driver Name : " + driver.getName());
//        System.out.println("Driver Plat No : " + driver.getPlatNo());
//        System.out.println("Total Price : " + price);
//        System.out.println("=== Thanks ===");
    }
}
