package com.dansmulti.ojolthree.view;

import com.dansmulti.ojolthree.model.*;
import com.dansmulti.ojolthree.service.FoodService;
import com.dansmulti.ojolthree.util.ScannerUtil;

import java.util.ArrayList;
import java.util.List;

public class FoodView {

    private final FoodService foodService;
    private List<CartItem> cartItems = new ArrayList<>();
    private Cart cart = new Cart(cartItems);
    private Restaurant restaurant;

    public FoodView(FoodService foodService) {
        this.foodService = foodService;
    }

    void show() {
        System.out.println("\n==== Food ====");
        System.out.println("1. Add menu");
        System.out.println("2. Check my cart");
        System.out.println("3. Exit");

        int options = ScannerUtil.scanLimitedOption("Select [1-3] : ", 3);
        if (options == 1) {
            addItemOption();
        } else if (options == 2) {
            showCartOption();
        } else if (options == 3) {
            return;
        }
        show();

        //  showReceipt();
    }

    private void addItemOption() {
        if (cart.getItems().isEmpty()) {
            showRestaurants();
            showRestaurantMenu();
            return;
        }
        showRestaurantMenu();
    }

    private void showCartOption() {
        if (cart.getItems().isEmpty()) {
            return;
        }
        showCart();
    }

    private void showRestaurants() {
        List<Restaurant> restaurants = foodService.getRestaurants();
        System.out.println("===== Food =====");
        System.out.println("Available restaurant");
        for (int i = 0; i < restaurants.size(); i++) {
            System.out.println((i + 1) + ". " + restaurants.get(i).getName() + " (" + restaurants.get(i).getAddress() + ")");
        }
        restaurant = restaurants.get(ScannerUtil.scanLimitedOption("\nSelect a restaurant : ", restaurants.size()) - 1);
    }


    private void showRestaurantMenu() {
        List<Menu> restaurantMenu = restaurant.getMenus();
        System.out.println("\n===== Menu in " + restaurant.getName() + " =====");
        for (int i = 0; i < restaurantMenu.size(); i++) {
            System.out.println((i + 1) + ". " + restaurantMenu.get(i).getName() + " (Rp. " + restaurantMenu.get(i).getPrice() + ")");
        }
        Menu menu = restaurantMenu.get(ScannerUtil.scanLimitedOption("\nSelect a menu : ", restaurantMenu.size()) - 1);
        int menuQty = ScannerUtil.scanInt("Input quantity : ");
        CartItem newItem = new CartItem(menu, menuQty, menu.getPrice() * menuQty);

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

    private void showReceipt() {
        String to = ScannerUtil.scanText("To : ");
        Driver driver = foodService.findDriver();

        System.out.println("\n======= Detail =======");
        System.out.println("Food : ");
        for (CartItem cartItem : cartItems) {
            System.out.println("- " + cartItem.getMenu().getName() + " " + cartItem.getQuantity() + "x@" + cartItem.getMenu().getPrice() + " (" + cartItem.getSubtotal() + ")");
        }
        System.out.println("Food Total Price : " + foodService.getCartGrandtotal(cart));
        System.out.println("From : " + restaurant.getName() + " (" + restaurant.getAddress() + ")");
        System.out.println("To : " + to);
        System.out.println("Driver Name : " + driver.getName());
        System.out.println("Driver Plat No : " + driver.getPlatNo());
        System.out.println("Total Price : " + foodService.calculateBill(cart, restaurant.getAddress(), to));
        System.out.println("======= Thanks =======\n");
    }
}
