package com.dansmulti.ojolone.view;

import com.dansmulti.ojolone.model.Driver;
import com.dansmulti.ojolone.model.Menu;
import com.dansmulti.ojolone.model.Restaurant;
import com.dansmulti.ojolone.service.FoodService;

import java.util.List;
import java.util.Scanner;

public class FoodView {

    private final FoodService foodService;

    public FoodView(FoodService foodService) {
        this.foodService = foodService;
    }

    void show() {
        Scanner scannerStr = new Scanner(System.in);
        Scanner scannerDbl = new Scanner(System.in);
        System.out.println("=== Food ====");

        List<Restaurant> restaurants = foodService.getRestaurants();

        System.out.println("Available restaurant");
        for (int i = 0; i < restaurants.size(); i++) {
            System.out.println((i + 1) + ". " + restaurants.get(i).getName() + " (" + restaurants.get(i).getAddress() + ")");
        }

        System.out.print("Select a restaurant : ");
        int idxRestaurant = scannerDbl.nextInt() - 1;

        String restaurantName = restaurants.get(idxRestaurant).getName();
        String restaurantAddress = restaurants.get(idxRestaurant).getAddress();

        System.out.println("=== Menu in " + restaurants.get(idxRestaurant).getName() + " ===");
        List<Menu> restaurantMenu = restaurants.get(idxRestaurant).getMenus();
        for (int i = 0; i < restaurantMenu.size(); i++) {
            System.out.println((i + 1) + ". " + restaurantMenu.get(i).getName() + " (Rp. " + restaurantMenu.get(i).getPrice() + ")");
        }
        System.out.print("Select a menu : ");
        int idxMenu = scannerDbl.nextInt() - 1;
        System.out.print("Input quantity : ");
        int qty = scannerDbl.nextInt();
        double price = restaurantMenu.get(idxMenu).getPrice();

        System.out.print("To : ");
        String to = scannerStr.nextLine();

        Driver driver = foodService.findDriver();
        double bill = foodService.calculateBill(restaurantAddress, to, qty, price);

        System.out.println("=== Detail ====");
        System.out.println("Food : " + restaurantMenu.get(idxMenu).getName() + " (" + qty + " x @" + restaurantMenu.get(idxMenu).getPrice() + ")");
        System.out.println("From : " + restaurantName + " (" + restaurantAddress + ")");
        System.out.println("To : " + to);
        System.out.println("Driver Name : " + driver.getName());
        System.out.println("Driver Plat No : " + driver.getPlatNo());
        System.out.println("Total Price : " + bill);
        System.out.println("=== Thanks ===");
    }
}
