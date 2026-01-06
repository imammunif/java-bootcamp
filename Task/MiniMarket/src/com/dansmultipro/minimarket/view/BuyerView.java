package com.dansmultipro.minimarket.view;

import com.dansmultipro.minimarket.listener.OnBackListener;
import com.dansmultipro.minimarket.model.CartItem;
import com.dansmultipro.minimarket.model.Category;
import com.dansmultipro.minimarket.model.Product;
import com.dansmultipro.minimarket.service.BuyerService;
import com.dansmultipro.minimarket.service.MarketService;
import com.dansmultipro.minimarket.util.ScannerUtil;

import java.util.List;

public class BuyerView {

    private final MarketService marketService;
    private final BuyerService buyerService;

    public BuyerView(MarketService marketService, BuyerService buyerService) {
        this.marketService = marketService;
        this.buyerService = buyerService;
    }

    public void show(OnBackListener listener) {
        System.out.println("""
                [1] Show products
                [2] Show my cart
                [3] Show my history
                [4] Switch user
                [0] Exit""");
        int chosen = ScannerUtil.scanIntegerLimited("Select an option [0-4] : ", 4, "Invalid option");
        if (chosen == 1) {
            showCategory(listener);
        } else if (chosen == 2) {
            showCart();
        } else if (chosen == 3) {
            showHistory();
        } else if (chosen == 4) {
            switchUser();
        } else if (chosen == 0) {
            return;
        }
        show(listener);
    }


    private void showCategory(OnBackListener listener) {
        List<Category> catalogList = marketService.getCatalogList();
        if (catalogList.isEmpty()) {
            System.out.println("Sorry, currently no any product category available");
            return;
        }
        System.out.println("---- Available Product Category ----");
        for (int i = 0; i < catalogList.size(); i++) {
            System.out.println((i + 1) + ". " + "Category: " + catalogList.get(i).getName());
        }
        System.out.println("[1-" + catalogList.size() + "] Select a category");
        System.out.println("[0] Back to main");
        int input = ScannerUtil.scanIntegerLimited("Select : ", catalogList.size(), "Invalid option");
        if (input == 0) {
            return;
        }
        Category category = catalogList.get(input - 1);
        if (category.getProducts().isEmpty()) {
            System.out.println("Sorry, this category has no product");
        }
        boolean added = showProducts(category, listener);
        if (added) {
            String addMore = ScannerUtil.scanText("Add another product? [y/n] : ");
            if ("y".equalsIgnoreCase(addMore)) {
                showCategory(listener);
            } else {
                return;
            }
        }
        show(listener);
    }


    private boolean showProducts(Category category, OnBackListener listener) {
        List<Product> products = category.getProducts();
        for (int i = 0; i < products.size(); i++) {
            System.out.println((i + 1) + ". " + products.get(i).getName() + " (@" + products.get(i).getPrice() + ") Stock: " + products.get(i).getStock());
        }
        System.out.println("[1-" + products.size() + "] Select a product");
        System.out.println("[0] Back to main");
        int input = ScannerUtil.scanIntegerLimited("Select : ", products.size(), "Invalid option");
        if (input == 0) {
            return false;
        }
        Product product = products.get(input - 1);
        if (product.getStock() == 0) {
            System.out.println("Oops, product out of stock!");
            return false;
        }
        int productQty = ScannerUtil.scanIntegerLimited("Enter quantity : ", product.getStock(), "Invalid quantity");
        marketService.updateProductStock(product, (productQty * -1));
        CartItem newItem = new CartItem(product, productQty, product.getPrice() * productQty);
        buyerService.addOrUpdateCartItem(newItem);
        System.out.println("Product successfully added to your cart");
        return true;
    }

    private void switchUser() {
    }

    private void showHistory() {
    }

    private void showCart() {
    }
}