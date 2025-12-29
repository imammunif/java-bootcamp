package com.dansmulti.ojolthree.view;

import com.dansmulti.ojolthree.model.*;
import com.dansmulti.ojolthree.service.FoodService;
import com.dansmulti.ojolthree.util.ScannerUtil;

import java.util.ArrayList;
import java.util.List;

public class FoodView {

    private final FoodService foodService;
    private List<CartItem> cartItems;
    private Cart cart;
    private Restaurant restaurant;
    private Driver driver;

    public FoodView(FoodService foodService) {
        this.foodService = foodService;
    }

    void show() {
        cartItems = new ArrayList<>();
        cart = new Cart(cartItems);

        showRestaurants();
        showRestaurantMenu();
        showCart();
        String to = ScannerUtil.scanText("To : ");
        driver = foodService.findDriver();
        double totalBill = foodService.calculateBill(cart, restaurant.getAddress(), to);
        showReceipt(driver.getName(), driver.getPlatNo(), to, totalBill);
    }

    private void showRestaurants() {
        List<Restaurant> restaurants = foodService.getRestaurants();
        System.out.println("===== Food =====");
        System.out.println("Available restaurant");
        for (int i = 0; i < restaurants.size(); i++) {
            System.out.println((i + 1) + ". " + restaurants.get(i).getName() + " (" + restaurants.get(i).getAddress() + ")");
        }
        int indexResto = ScannerUtil.scanLimitedOption("\nSelect a restaurant : ", restaurants.size()) - 1;
        restaurant = restaurants.get(indexResto);
    }


    private void showRestaurantMenu() {
        List<Menu> restaurantMenu = restaurant.getMenus();
        System.out.println("\n===== Menu in " + restaurant.getName() + " =====");
        for (int i = 0; i < restaurantMenu.size(); i++) {
            System.out.println((i + 1) + ". " + restaurantMenu.get(i).getName() + " (Rp. " + restaurantMenu.get(i).getPrice() + ")");
        }
        int indexMenu = ScannerUtil.scanLimitedOption("\nSelect a menu : ", restaurantMenu.size()) - 1;
        Menu menu = restaurantMenu.get(indexMenu);
        int menuQty = ScannerUtil.scanInt("Input quantity : ");
        CartItem newItem = new CartItem(menu, menuQty, restaurantMenu.get(indexMenu).getPrice() * menuQty);
        foodService.setCartItems(cart, newItem, menu, menuQty);
        foodService.setCartRestaurant(cart, restaurant);
        String addMore = ScannerUtil.scanText("\nDo you want to add more? [Y/N] : ");
        if ("y".equalsIgnoreCase(addMore)) {
            showRestaurantMenu();
        }
    }

    private void showCart() {
        System.out.println("\n===== Your cart from : " + restaurant.getName() + " =====");
        for (CartItem cartItem : cartItems) {
            System.out.println("- " + cartItem.getMenu().getName() + " " + cartItem.getQuantity() + "x@" + cartItem.getMenu().getPrice() + " (" + cartItem.getSubtotal() + ")");
        }
    }

    private void showReceipt(String driverName, String driverLicense, String to, double totalBill) {
        System.out.println("\n======= Detail =======");
        System.out.println("Food : ");
        for (CartItem cartItem : cartItems) {
            System.out.println("- " + cartItem.getMenu().getName() + " " + cartItem.getQuantity() + "x@" + cartItem.getMenu().getPrice() + " (" + cartItem.getSubtotal() + ")");
        }
        System.out.println("Food Total Price : " + foodService.getCartGrandtotal(cart));
        System.out.println("From : " + restaurant.getName() + " (" + restaurant.getAddress() + ")");
        System.out.println("To : " + to);
        System.out.println("Driver Name : " + driverName);
        System.out.println("Driver Plat No : " + driverLicense);
        System.out.println("Total Price : " + totalBill);
        System.out.println("======= Thanks =======\n");
    }
}
