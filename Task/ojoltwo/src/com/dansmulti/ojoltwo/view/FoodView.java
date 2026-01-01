package com.dansmulti.ojoltwo.view;

import com.dansmulti.ojoltwo.model.*;
import com.dansmulti.ojoltwo.service.FoodService;
import com.dansmulti.ojoltwo.util.ScannerUtil;

import java.util.ArrayList;
import java.util.List;

public class FoodView {

    private final FoodService foodService;
    private List<CartItem> cartItems;
    private Cart cart;

    public FoodView(FoodService foodService) {
        this.foodService = foodService;
    }

    void show() {
        cartItems = new ArrayList<>();
        cart = new Cart(cartItems);
        List<Restaurant> restaurants = foodService.getRestaurants();
        showRestaurants(restaurants);
        int indexResto = ScannerUtil.scanLimitedOption("\nSelect a restaurant : ", restaurants.size()) - 1;
        List<Menu> restaurantMenu = restaurants.get(indexResto).getMenus();
        String restoName = restaurants.get(indexResto).getName();
        String restoAddress = restaurants.get(indexResto).getAddress();

        showRestaurantMenu(restoName, restaurantMenu);
        showCart(restoName);
        String to = ScannerUtil.scanText("To : ");
        Driver driver = foodService.findDriver();
        double totalBill = foodService.calculateBill(cart, restoAddress, to);
        showReceipt(restoName, restoAddress, driver.getName(), driver.getPlatNo(), to, totalBill);
    }

    private void showRestaurants(List<Restaurant> restaurants) {
        System.out.println("===== Food =====");
        System.out.println("Available restaurant");
        for (int i = 0; i < restaurants.size(); i++) {
            System.out.println((i + 1) + ". " + restaurants.get(i).getName() + " (" + restaurants.get(i).getAddress() + ")");
        }
    }


    private void showRestaurantMenu(String restoName, List<Menu> restaurantMenu) {
        System.out.println("\n===== Menu in " + restoName + " =====");
        for (int i = 0; i < restaurantMenu.size(); i++) {
            System.out.println((i + 1) + ". " + restaurantMenu.get(i).getName() + " (Rp. " + restaurantMenu.get(i).getPrice() + ")");
        }
        int indexMenu = ScannerUtil.scanLimitedOption("\nSelect a menu : ", restaurantMenu.size()) - 1;
        Menu menu = restaurantMenu.get(indexMenu);
        int menuQty = ScannerUtil.scanInt("Input quantity : ");
        CartItem newItem = new CartItem(menu, menuQty, restaurantMenu.get(indexMenu).getPrice() * menuQty);
        foodService.setCartItems(cart, newItem, menu, menuQty);
        String addMore = ScannerUtil.scanText("\nDo you want to add more? [Y/N] : ");
        if ("y".equalsIgnoreCase(addMore)) {
            showRestaurantMenu(restoName, restaurantMenu);
        }
    }

    private void showCart(String restoName) {
        System.out.println("\n===== Your cart from : " + restoName + " =====");
        for (CartItem cartItem : cartItems) {
            System.out.println("- " + cartItem.getMenu().getName() + " " + cartItem.getQuantity() + "x@" + cartItem.getMenu().getPrice() + " (" + cartItem.getSubtotal() + ")");
        }
    }

    private void showReceipt(String restoName, String restoAddress, String driverName, String driverLicense, String to, double totalBill) {
        System.out.println("\n======= Detail =======");
        System.out.println("Food : ");
        for (CartItem cartItem : cartItems) {
            System.out.println("- " + cartItem.getMenu().getName() + " " + cartItem.getQuantity() + "x@" + cartItem.getMenu().getPrice() + " (" + cartItem.getSubtotal() + ")");
        }
        System.out.println("Food Total Price : " + foodService.getCartGrandtotal(cart));
        System.out.println("From : " + restoName + " (" + restoAddress + ")");
        System.out.println("To : " + to);
        System.out.println("Driver Name : " + driverName);
        System.out.println("Driver Plat No : " + driverLicense);
        System.out.println("Total Price : " + totalBill);
        System.out.println("======= Thanks =======\n");
    }
}
