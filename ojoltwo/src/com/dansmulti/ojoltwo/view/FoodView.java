package com.dansmulti.ojoltwo.view;

import com.dansmulti.ojoltwo.model.*;
import com.dansmulti.ojoltwo.service.FoodService;
import com.dansmulti.ojoltwo.util.ScannerUtil;

import java.util.ArrayList;
import java.util.List;

public class FoodView {

    private final FoodService foodService;
    private List<CartItem> cartItems = new ArrayList<>();
    private Cart cart = new Cart(cartItems);

    public FoodView(FoodService foodService) {
        this.foodService = foodService;
    }

    void show() {
        boolean isConfirmed = false;
        List<Restaurant> restaurants = foodService.getRestaurants();
        showRestaurants(restaurants);
        int indexResto = ScannerUtil.scanLimitedOption("\nSelect a restaurant : ", restaurants.size()) - 1;
        List<Menu> restaurantMenu = restaurants.get(indexResto).getMenus();
        String restoName = restaurants.get(indexResto).getName();
        String restoAddress = restaurants.get(indexResto).getAddress();

        do {
            showRestaurantMenu(restoName, restaurantMenu);
            int indexMenu = ScannerUtil.scanLimitedOption("\nSelect a menu : ", restaurantMenu.size()) - 1;
            Menu menu = restaurantMenu.get(indexMenu);
            int menuQty = ScannerUtil.scanInt("Input quantity : ");
            double menuPrice = restaurantMenu.get(indexMenu).getPrice();
            CartItem newItem = new CartItem(menu, menuQty, menuPrice * menuQty);
            addOrUpdateItem(newItem, menu, menuQty);
            showCart(restoName);

            String addMore = ScannerUtil.scanText("\nDo you want to add more? [Y/N] : ");
            if (addMore.equalsIgnoreCase("n")) {
                isConfirmed = true;
            }
        } while (!isConfirmed);

        String to = ScannerUtil.scanText("To : ");
        Driver driver = foodService.findDriver();
        double totalBill = foodService.calculateBill(restoAddress, to, cart.getGrandTotal());
        showReceipt(restoName, restoAddress, driver.getName(), driver.getPlatNo(), to, totalBill);
    }

    private void addOrUpdateItem(CartItem newItem, Menu menu, int menuQty) {
        for (int i = 0; i < cartItems.size(); i++) {
            if (cartItems.get(i).getMenu().equals(menu)) {
                cartItems.get(i).setQuantity(menuQty);
                cartItems.get(i).setSubtotal();
                return;
            }
        }
        cart.addItem(newItem);
    }

    private void showRestaurants(List<Restaurant> restaurants) {
        System.out.println("===== Food =====");
        System.out.println("Available restaurant");
        for (int i = 0; i < restaurants.size(); i++) {
            System.out.println((i + 1) + ". " + restaurants.get(i).getName() + " (" + restaurants.get(i).getAddress() + ")");
        }
    }


    void showRestaurantMenu(String restoName, List<Menu> restaurantMenu) {
        System.out.println("\n===== Menu in " + restoName + " =====");
        for (int i = 0; i < restaurantMenu.size(); i++) {
            System.out.println((i + 1) + ". " + restaurantMenu.get(i).getName() + " (Rp. " + restaurantMenu.get(i).getPrice() + ")");
        }
    }

    void showCart(String restoName) {
        System.out.println("\n===== Your cart from : " + restoName + " =====");
        for (int i = 0; i < cartItems.size(); i++) {
            System.out.println("- " + cartItems.get(i).getMenu().getName() + " " + cartItems.get(i).getQuantity() + "x@" + cartItems.get(i).getMenu().getPrice() + " (" + cartItems.get(i).getSubtotal() + ")");
        }
    }

    void showReceipt(String restoName, String restoAddress, String driverName, String driverLicense, String to, double totalBill) {
        System.out.println("\n======= Detail =======");
        System.out.println("Food : ");
        for (int i = 0; i < cartItems.size(); i++) {
            System.out.println("- " + cartItems.get(i).getMenu().getName() + " " + cartItems.get(i).getQuantity() + "x@" + cartItems.get(i).getMenu().getPrice() + " (" + cartItems.get(i).getSubtotal() + ")");
        }
        System.out.println("From : " + restoName + " (" + restoAddress + ")");
        System.out.println("To : " + to);
        System.out.println("Driver Name : " + driverName);
        System.out.println("Driver Plat No : " + driverLicense);
        System.out.println("Total Price : " + totalBill);
        System.out.println("======= Thanks =======\n");
    }
}
