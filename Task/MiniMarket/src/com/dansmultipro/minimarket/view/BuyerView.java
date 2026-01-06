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
            showCart(listener);
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
        List<Category> catalogList = marketService.getCategories();
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

    private void showCart(OnBackListener listener) {
        List<CartItem> cartItems = buyerService.getCartItems();
        System.out.println(cartItems);
        if (cartItems.isEmpty()) {
            System.out.println("Your cart is empty. Please add a product first!");
            return;
        }
        System.out.println("--- Showing your cart ---");
        for (CartItem cartItem : cartItems) {
            System.out.println("- " + cartItem.getProduct().getName() + " " + cartItem.getQuantity() + "x@" + cartItem.getProduct().getPrice() + " (" + cartItem.getSubtotal() + ")");
        }
        System.out.println("""
                Cart options :
                [1] Edit cart
                [2] Checkout to an order
                [0] Back""");
        int options = ScannerUtil.scanIntegerLimited("Select an option [1-3] : ", 2, "Invalid option");
        if (options == 1) {
            editCart(cartItems);
        } else if (options == 2) {
            checkout(listener, cartItems);
        } else if (options == 0) {
            return;
        }
        listener.onBackPressed();
    }

    private void editCart(List<CartItem> cartItems) {
        System.out.println("Editing options :");
        System.out.println("[1] Edit quantity");
        System.out.println("[2] Delete per item");
        System.out.println("[3] Delete all");
        int options = ScannerUtil.scanIntegerLimited("Select [1-3] : ", 3, "Invalid option");
        if (options == 1) {
            showEditQuantity(cartItems);
        } else if (options == 2) {
            showDeletePerItem(cartItems);
        } else if (options == 3) {
            showDeleteAllItems(cartItems);
        }
    }

    private void showDeletePerItem(List<CartItem> cartItems) {
        for (int i = 0; i < cartItems.size(); i++) {
            System.out.println((i + 1) + ". " + cartItems.get(i).getProduct().getName() + " x" + cartItems.get(i).getQuantity());
        }
        int input = ScannerUtil.scanIntegerLimited("\nSelect item number to delete :", cartItems.size(), "Invalid product");
        CartItem item = cartItems.get(input - 1);
//        Product product = products.stream()
//                .filter(p -> p.getName().equalsIgnoreCase(item.getProduct().getName()))
//                .findFirst()
//                .orElse(null);
//        if (product != null) {
//            deleteSingleItem(cartItems, product, item);
//        }
    }

    private void showEditQuantity(List<CartItem> cartItems) {
    }

    private void showDeleteAllItems(List<CartItem> cartItems) {
    }

    private void checkout(OnBackListener listener, List<CartItem> cartItems) {

    }
}