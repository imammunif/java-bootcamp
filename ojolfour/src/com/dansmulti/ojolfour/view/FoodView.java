package com.dansmulti.ojolfour.view;

import com.dansmulti.ojolfour.listener.OnBackListener;
import com.dansmulti.ojolfour.model.*;
import com.dansmulti.ojolfour.model.order.FoodOrder;
import com.dansmulti.ojolfour.service.FoodService;
import com.dansmulti.ojolfour.service.HistoryService;
import com.dansmulti.ojolfour.util.ScannerUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FoodView {

    private final FoodService foodService;
    private final HistoryService historyService;
    private List<CartItem> cartItems = new ArrayList<>();
    private Cart cart = new Cart(cartItems);
    private Restaurant restaurant;
    private OnBackListener onBackListener;

    public FoodView(FoodService foodService, HistoryService historyService) {
        this.foodService = foodService;
        this.historyService = historyService;
    }

    void show(History history, OnBackListener listener) {
        this.onBackListener = listener;
        System.out.println("\n==== Food ====");
        System.out.println("1. Show menu");
        System.out.println("2. Show cart");
        System.out.println("3. Back");

        int options = ScannerUtil.scanLimitedOption("\nSelect [1-3] : ", 3);
        if (options == 1) {
            itemSubmenu();
        } else if (options == 2) {
            cartSubmenu(history);
        } else if (options == 3) {
            listener.onBackPressed();
        }
        show(history, listener);
    }

    private void itemSubmenu() {
        if (cart.getItems().isEmpty()) {
            showRestaurants();
            showRestaurantMenu();
            return;
        }
        showRestaurantMenu();
    }

    private void cartSubmenu(History history) {
        if (cart.getItems().isEmpty()) {
            System.out.println("\n===== Your cart is empty. Please add an item first! =====");
            return;
        }
        showCart(history);
    }

    private void showRestaurants() {
        List<Restaurant> restaurants = foodService.getRestaurants();
        System.out.println("Available restaurants:");
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
        int menuQty = ScannerUtil.scanInt("Enter quantity : ");
        CartItem newItem = new CartItem(menu, menuQty, menu.getPrice() * menuQty);

        foodService.setCartItems(cart, newItem, menu, menuQty);
        foodService.setCartRestaurant(cart, restaurant);
        String addMore = ScannerUtil.scanText("\nDo you want to add more? [y/n] : ");
        if ("y".equalsIgnoreCase(addMore)) {
            showRestaurantMenu();
        }
    }

    private void showCart(History history) {
        System.out.println("\n===== Your cart from : " + restaurant.getName() + " =====");
        for (CartItem cartItem : cartItems) {
            System.out.println("- " + cartItem.getMenu().getName() + " " + cartItem.getQuantity() + "x@" + cartItem.getMenu().getPrice() + " (" + cartItem.getSubtotal() + ")");
        }
        System.out.println("Cart options :");
        System.out.println("1. Edit cart");
        System.out.println("2. Checkout");
        System.out.println("3. Back");

        int options = ScannerUtil.scanLimitedOption("\nSelect [1-3] : ", 3);
        if (options == 1) {
            editCart();
        } else if (options == 2) {
            checkout(history);
        } else if (options == 3) {
            return;
        }
        show(history, onBackListener);
    }

    private void editCart() {
        System.out.println("Editing options :");
        System.out.println("1. Edit quantity");
        System.out.println("2. Delete per item");
        System.out.println("3. Delete all");
        int options = ScannerUtil.scanLimitedOption("\nSelect [1-3] : ", 3);
        if (options == 1) {
            editQuantity();
        } else if (options == 2) {
            deletePerItem();
        } else if (options == 3) {
            deleteAllItem();
        }
    }

    private void deleteAllItem() {
        cartItems.clear();
        System.out.println("All items deleted successfully");
    }

    private void deletePerItem() {
        for (int i = 0; i < cartItems.size(); i++) {
            System.out.println((i + 1) + ". " + cartItems.get(i).getMenu().getName() + " x" + cartItems.get(i).getQuantity());
        }
        int input = ScannerUtil.scanLimitedOption("\nSelect item number to delete :", cartItems.size());
        cartItems.remove(input - 1);
        System.out.println("Item deleted successfully");
    }

    private void editQuantity() {
        for (int i = 0; i < cartItems.size(); i++) {
            System.out.println((i + 1) + ". " + cartItems.get(i).getMenu().getName() + " x" + cartItems.get(i).getQuantity());
        }
        int input = ScannerUtil.scanLimitedOption("\nSelect item number to edit : ", cartItems.size());
        CartItem item = cartItems.get(input - 1);
        int availQty = item.getQuantity();
        int targetQty = ScannerUtil.scanInt("Enter new quantity : ");
        if (targetQty > availQty) {
            foodService.setItemQty(item, (targetQty - availQty));
        } else {
            foodService.setItemQty(item, ((availQty - targetQty) * -1));
        }
        foodService.setCartGrandtotal(cart);
        System.out.println("Item updated successfully");

    }

    private void checkout(History history) {
        String to = ScannerUtil.scanText("To : ");
        Driver driver = foodService.findDriver();

        System.out.println("\n======= Detail =======");
        System.out.println("Food : ");
        for (CartItem cartItem : cartItems) {
            System.out.println("- " + cartItem.getMenu().getName() + " " + cartItem.getQuantity() + "x@" + cartItem.getMenu().getPrice() + " (" + cartItem.getSubtotal() + ")");
        }
        System.out.println("From : " + restaurant.getName() + " (" + restaurant.getAddress() + ")");
        System.out.println("To : " + to);
        System.out.println("Driver Name : " + driver.getName());
        System.out.println("Driver Plat No : " + driver.getPlatNo());
        System.out.println("Total Price : " + foodService.calculateBill(cart, restaurant.getAddress(), to));
        System.out.println("======= Thanks =======");

        FoodOrder foodOrder = new FoodOrder("Food", LocalDateTime.now(), restaurant.getAddress(), to);
        historyService.setOrderHistory(history, foodOrder);

        cartItems.clear();
    }
}
