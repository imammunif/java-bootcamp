package com.dansmultipro.minimarket.view;

import com.dansmultipro.minimarket.listener.OnBackListener;
import com.dansmultipro.minimarket.model.Category;
import com.dansmultipro.minimarket.service.MarketService;
import com.dansmultipro.minimarket.service.SellerService;
import com.dansmultipro.minimarket.util.ScannerUtil;

import java.util.ArrayList;
import java.util.List;

public class SellerView {

    private final MarketService marketService;
    private final SellerService sellerService;

    public SellerView(MarketService marketService, SellerService sellerService) {
        this.marketService = marketService;
        this.sellerService = sellerService;
    }

    public void show(OnBackListener listener) {
        System.out.println("""
                [1] Show category
                [0] Exit""");
        int chosen = ScannerUtil.scanIntegerLimited("Select an option [0-4] : ", 4, "Invalid option");
        if (chosen == 1) {
            showCategory(listener);
        } else if (chosen == 0) {
            listener.onBackPressed();
        }
        show(listener);
    }

    private void showCategory(OnBackListener listener) {
        List<Category> categories = marketService.getCategories();
        System.out.println("---- Showing Product Categories ----");
        for (int i = 0; i < categories.size(); i++) {
            System.out.println((i + 1) + ". " + categories.get(i).getName());
        }
        if (categories.isEmpty()) {
            System.out.println("\nYou haven't set any product category yet...\n");
        }
        System.out.println("[1] Input category [0] Back to main");
        int input = ScannerUtil.scanIntegerLimited("Select : ", 1, "Invalid option");
        if (input == 0) {
            return;
        }
        String categoryName = ScannerUtil.scanText("Enter new category name :");
        Category newCategory = new Category(categoryName, new ArrayList<>());
        boolean isCategoryAdded = marketService.addCategory(newCategory);
        if (!isCategoryAdded) {
            System.out.println("Oops, category is already exist!");
        }
        System.out.println("Category successfully added");
    }
}
