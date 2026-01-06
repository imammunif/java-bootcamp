package com.dansmultipro.minimarket.view;

import com.dansmultipro.minimarket.listener.OnBackListener;
import com.dansmultipro.minimarket.model.CartItem;
import com.dansmultipro.minimarket.model.Category;
import com.dansmultipro.minimarket.model.Order;
import com.dansmultipro.minimarket.model.Product;
import com.dansmultipro.minimarket.service.BuyerService;
import com.dansmultipro.minimarket.service.MarketService;
import com.dansmultipro.minimarket.util.RandomSequence;
import com.dansmultipro.minimarket.util.ScannerUtil;

import java.time.LocalDateTime;
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
            showHistory(listener);
        } else if (chosen == 4) {
            switchUser();
        } else if (chosen == 0) {
            listener.onBackPressed();
        }
        show(listener);
    }


    private void showCategory(OnBackListener listener) {
        List<Category> categories = marketService.getCategories();
        if (categories.isEmpty()) {
            System.out.println("Sorry, currently no any product category available");
            return;
        }
        System.out.println("---- Available Product Category ----");
        for (int i = 0; i < categories.size(); i++) {
            System.out.println((i + 1) + ". " + "Category " + categories.get(i).getName());
        }
        System.out.println("[1-" + categories.size() + "] Select a category [0] Back to main");
        int input = ScannerUtil.scanIntegerLimited("Select an option : ", categories.size(), "Invalid option");
        if (input == 0) {
            return;
        }
        Category category = categories.get(input - 1);
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
        System.out.println("[1-" + products.size() + "] Select a product [0] Back to main");
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

    private void showHistory(OnBackListener listener) {
        List<Order> myHistories = marketService.getHistories();
        if (myHistories.isEmpty()) {
            System.out.println("Your history is empty. Please checkout an order first!");
            return;
        }
        listener.onBackPressed();
    }

    private void showCart(OnBackListener listener) {
        List<CartItem> cartItems = buyerService.getCartItems();
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
        String checkoutApproval = ScannerUtil.scanText("Are you sure you want checkout all products in your cart? [y/n] : ");
        if ("y".equalsIgnoreCase(checkoutApproval)) {
            Order newOrder = new Order(RandomSequence.getAlphaNumericString(8), LocalDateTime.now(), buyerService.getCartGrandtotal());
            marketService.setOrderHistory(newOrder);
            printReceipt(cartItems);
            cartItems.clear();
            return;
        }
        show(listener);
    }

    private void printReceipt(List<CartItem> cartItems) {
        String haveDiscount = ScannerUtil.scanText("Do you have voucher [y/n] : ");
        String voucher = "";
        if ("y".equalsIgnoreCase(haveDiscount)) {
            voucher = ScannerUtil.scanText("Enter voucher : ");
        }
        System.out.println("Processing your order ...");
        System.out.println("\n-------------------------");
        for (CartItem cartItem : cartItems) {
            System.out.println(" - " + cartItem.getProduct().getName() + " " + cartItem.getQuantity() + "x@" + cartItem.getProduct().getPrice() + " (" + cartItem.getSubtotal() + ")");
        }
        System.out.println("  Total : " + buyerService.getCartGrandtotal());
        System.out.println("  Discount : " + buyerService.getCartGrandtotal() * buyerService.calculateDiscount(voucher) / 100);
        System.out.println("  Total billed : " + buyerService.calculateBil(voucher));
        System.out.println("-------------------------");
        System.out.println("Your order successfully checked out");
        System.out.println("Thank you :D\n");
    }
}