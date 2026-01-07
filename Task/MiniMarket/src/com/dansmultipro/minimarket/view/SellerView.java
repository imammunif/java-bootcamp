package com.dansmultipro.minimarket.view;

import com.dansmultipro.minimarket.listener.OnBackListener;
import com.dansmultipro.minimarket.model.Category;
import com.dansmultipro.minimarket.model.Order;
import com.dansmultipro.minimarket.model.Product;
import com.dansmultipro.minimarket.service.MarketService;
import com.dansmultipro.minimarket.util.ScannerUtil;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SellerView {

    private final MarketService marketService;
    DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    public SellerView(MarketService marketService) {
        this.marketService = marketService;
    }

    public void show(OnBackListener listener) {
        List<Category> categories = marketService.getCategories();
        System.out.println("""
                [1] Add category
                [2] Add products
                [3] Show buyer history
                [0] Logout""");
        int chosen = ScannerUtil.scanIntegerLimited("Select an option [0-4] : ", 3, "Invalid option");
        if (chosen == 1) {
            showCategory(categories);
        } else if (chosen == 2) {
            showProducts(categories);
        } else if (chosen == 3) {
            showHistory();
        } else if (chosen == 0) {
            listener.onBackPressed();
        }
        show(listener);
    }

    private void showHistory() {
        List<Order> myHistories = marketService.getHistories();
        if (myHistories.isEmpty()) {
            System.out.println("No order history...");
            return;
        }
        System.out.println("--- Showing your order History ---");
        myHistories.stream()
                .sorted(Comparator.comparing(Order::getDateTime).reversed())
                .collect(Collectors.toList())
                .forEach(order -> System.out.println("Order ID: " + order.getSequence() + " Date: " + order.getDateTime().format(timeFormat) + " Total: " + order.getGrandTotal()));
    }

    private void showProducts(List<Category> categories) {
        System.out.println("-- Showing categories --");
        for (int i = 0; i < categories.size(); i++) {
            System.out.println((i + 1) + ". " + categories.get(i).getName());
        }
        int input = ScannerUtil.scanIntegerLimited("\nSelect product category to add : ", categories.size(), "Invalid category");
        Category category = categories.get(input - 1);
        List<Product> products = marketService.getProducts(category);
        System.out.println("-- Showing products of " + category.getName() + " --");
        if (products.isEmpty()) {
            System.out.println("Category " + category.getName() + " has no product...");
        }
        for (int i = 0; i < products.size(); i++) {
            System.out.println((i + 1) + ". " + products.get(i).getName() + " (@" + products.get(i).getPrice() + ") Stock: " + products.get(i).getStock());
        }
        String productName = ScannerUtil.scanText("Enter new product name : ");
        Double productPrice = ScannerUtil.scanDouble("Enter the price : ");
        int productQuantity = ScannerUtil.scanInt("Enter the quantity : ");
        Product newProduct = new Product(productName, productPrice, productQuantity, category);
        boolean isProductAdded = marketService.addProduct(category, newProduct);
        if (!isProductAdded) {
            System.out.println("Oops, product is already exist!");
        } else {
            System.out.println("Product successfully added");
        }
    }

    private void showCategory(List<Category> categories) {
        System.out.println("---- Showing Product Categories ----");
        for (int i = 0; i < categories.size(); i++) {
            System.out.println((i + 1) + ". " + "Category " + categories.get(i).getName());
        }
        if (categories.isEmpty()) {
            System.out.println("\nYou haven't set any product category yet...\n");
        }
        System.out.println("[1] Input category [0] Back to main");
        int input = ScannerUtil.scanIntegerLimited("Select : ", 1, "Invalid option");
        if (input == 0) {
            return;
        }
        String categoryName = ScannerUtil.scanText("Enter new category name : ");
        Category newCategory = new Category(categoryName, new ArrayList<>());
        boolean isCategoryAdded = marketService.addCategory(newCategory);
        if (!isCategoryAdded) {
            System.out.println("Oops, category is already exist!");
        } else {
            System.out.println("Category successfully added");
        }
    }
}
