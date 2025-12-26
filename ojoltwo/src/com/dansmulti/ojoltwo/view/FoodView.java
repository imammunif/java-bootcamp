package com.dansmulti.ojoltwo.view;

import com.dansmulti.ojoltwo.model.Driver;
import com.dansmulti.ojoltwo.model.Menu;
import com.dansmulti.ojoltwo.model.Restaurant;
import com.dansmulti.ojoltwo.service.FoodService;
import com.dansmulti.ojoltwo.util.ScannerUtil;

import java.util.List;

public class FoodView {

    private final FoodService foodService;

    public FoodView(FoodService foodService) {
        this.foodService = foodService;
    }

    void show() {
        System.out.println("===== Food =====");
        System.out.println("Available restaurant");
        List<Restaurant> restaurants = foodService.getRestaurants();
        for (int i = 0; i < restaurants.size(); i++) {
            System.out.println((i + 1) + ". " + restaurants.get(i).getName() + " (" + restaurants.get(i).getAddress() + ")");
        }
        int indexResto = ScannerUtil.scanLimitedOption("Select a restaurant : ", restaurants.size()) - 1;
        String restoName = restaurants.get(indexResto).getName();
        String restoAddress = restaurants.get(indexResto).getAddress();
        System.out.println("=== Menu in " + restoName + " ===");
        List<Menu> restaurantMenu = restaurants.get(indexResto).getMenus();
        for (int i = 0; i < restaurantMenu.size(); i++) {
            System.out.println((i + 1) + ". " + restaurantMenu.get(i).getName() + " (Rp. " + restaurantMenu.get(i).getPrice() + ")");
        }
        int indexMenu = ScannerUtil.scanLimitedOption("Select a menu : ", restaurantMenu.size()) - 1;
        int menuQty = ScannerUtil.scanInt("Input quantity : ");
        String menuName = restaurantMenu.get(indexMenu).getName();
        double menuPrice = restaurantMenu.get(indexMenu).getPrice();
        String to = ScannerUtil.scanText("To : ");
        Driver driver = foodService.findDriver();
        double totalBill = foodService.calculateBill(restoAddress, to, menuQty, menuPrice);

        showReceipt(restoName, restoAddress, menuName, menuPrice, menuQty, driver.getName(), driver.getPlatNo(), to, totalBill);
    }

    void showReceipt(
            String restoName, String restoAddress, String menuName, Double menuPrice, int menuQty,
            String driverName, String driverLicense, String to, double totalBill) {
        System.out.println("===== Detail =====");
        System.out.println("Food : " + menuName + " (" + menuQty + " x @" + menuPrice + ")");
        System.out.println("From : " + restoName + " (" + restoAddress + ")");
        System.out.println("To : " + to);
        System.out.println("Driver Name : " + driverName);
        System.out.println("Driver Plat No : " + driverLicense);
        System.out.println("Total Price : " + totalBill);
        System.out.println("===== Thanks =====");
    }
}
