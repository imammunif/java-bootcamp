package com.dansmultipro.ministore.view;

import com.dansmultipro.ministore.listener.OnBackListener;
import com.dansmultipro.ministore.model.CartItem;
import com.dansmultipro.ministore.model.Order;
import com.dansmultipro.ministore.model.Product;
import com.dansmultipro.ministore.service.HistoryService;
import com.dansmultipro.ministore.service.ProductService;
import com.dansmultipro.ministore.util.RandomSequence;
import com.dansmultipro.ministore.util.ScannerUtil;

import java.time.LocalDateTime;
import java.util.List;

public class CartView {

    private final ProductService productService;
    private final HistoryService historyService;

    public CartView(ProductService productService, HistoryService historyService) {
        this.productService = productService;
        this.historyService = historyService;
    }

    public void show(OnBackListener listener) {
        List<CartItem> cartItems = productService.getCartItems();
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

    private void checkout(OnBackListener listener, List<CartItem> cartItems) {
        String checkoutApproval = ScannerUtil.scanText("Are you sure you want checkout all products in your cart? [y/n] : ");
        if ("y".equalsIgnoreCase(checkoutApproval)) {
            Order newOrder = new Order(RandomSequence.getAlphaNumericString(8), LocalDateTime.now(), productService.getCartGrandtotal());
            historyService.setOrderHistory(newOrder);
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
        System.out.println("  Total : " + productService.getCartGrandtotal());
        System.out.println("  Discount : " + productService.getCartGrandtotal() * productService.calculateDiscount(voucher) / 100);
        System.out.println("  Total billed : " + productService.calculateBil(voucher));
        System.out.println("-------------------------");
        System.out.println("Your order successfully checked out");
        System.out.println("Thank you :D\n");
    }

    private void editCart(List<CartItem> cartItems) {
        List<Product> products = productService.getProducts();
        System.out.println("Editing options :");
        System.out.println("[1] Edit quantity");
        System.out.println("[2] Delete per item");
        System.out.println("[3] Delete all");
        int options = ScannerUtil.scanIntegerLimited("Select [1-3] : ", 3, "Invalid option");
        if (options == 1) {
            showEditQuantity(cartItems, products);
        } else if (options == 2) {
            showDeletePerItem(cartItems, products);
        } else if (options == 3) {
            showDeleteAllItems(cartItems, products);
        }
    }

    private void showDeleteAllItems(List<CartItem> cartItems, List<Product> products) {
        for (CartItem item : cartItems) {
            products.stream()
                    .filter(product -> product.getName().equalsIgnoreCase(item.getProduct().getName()))
                    .forEach(product -> {
                        System.out.println("***** Debug: Update stock the same product ***** [" + product.getName() + "]");
                        productService.updateProductStock(product, item.getQuantity());
                    });
        }
        cartItems.clear();
        System.out.println("All products deleted successfully");
    }

    private void showDeletePerItem(List<CartItem> cartItems, List<Product> products) {
        for (int i = 0; i < cartItems.size(); i++) {
            System.out.println((i + 1) + ". " + cartItems.get(i).getProduct().getName() + " x" + cartItems.get(i).getQuantity());
        }
        int input = ScannerUtil.scanIntegerLimited("\nSelect product number to delete :", cartItems.size(), "Invalid product");
        CartItem item = cartItems.get(input - 1);
        Product product = products.stream()
                .filter(p -> p.getName().equalsIgnoreCase(item.getProduct().getName()))
                .findFirst()
                .orElse(null);
        if (product != null) {
            deleteSingleItem(cartItems, product, item);
        }
    }

    private void deleteSingleItem(List<CartItem> cartItems, Product product, CartItem item) {
        productService.updateProductStock(product, (item.getQuantity()));
        cartItems.remove(item);
        System.out.println("***** Debug: Update stock the same product ***** [" + product.getName() + "]");
        System.out.println("Product deleted successfully");
    }

    private void showEditQuantity(List<CartItem> cartItems, List<Product> products) {
        for (int i = 0; i < cartItems.size(); i++) {
            System.out.println((i + 1) + ". " + cartItems.get(i).getProduct().getName() + " x" + cartItems.get(i).getQuantity());
        }
        int input = ScannerUtil.scanIntegerLimited("\nSelect product number to edit : ", cartItems.size(), "Invalid product");
        CartItem item = cartItems.get(input - 1);
        Product product = products.stream()
                .filter(p -> p.getName().equalsIgnoreCase(item.getProduct().getName()))
                .findFirst()
                .orElse(null);

        int itemQty = item.getQuantity();
        int targetQty = ScannerUtil.scanIntegerLimited("Enter new quantity : ", 1000, "Invalid quantity");
        int diffQty = targetQty - itemQty;
        if (product != null && diffQty > product.getStock()) {
            System.out.println("Can't exceeded available stock in the catalog");
            return;
        } else if (targetQty == 0) {
            deleteSingleItem(cartItems, product, item);
            return;
        }
        productService.updateItemQuantity(item, diffQty); //+
        productService.updateProductStock(product, (diffQty * -1)); //-
        productService.updateCartGrandtotal();
        System.out.println("Item updated successfully");
    }
}
