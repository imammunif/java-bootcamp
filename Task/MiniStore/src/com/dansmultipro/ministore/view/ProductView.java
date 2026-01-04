package com.dansmultipro.ministore.view;

import com.dansmultipro.ministore.listener.OnBackListener;
import com.dansmultipro.ministore.model.CartItem;
import com.dansmultipro.ministore.model.Product;
import com.dansmultipro.ministore.service.ProductService;
import com.dansmultipro.ministore.util.ScannerUtil;

import java.util.List;

public class ProductView {

    private final ProductService productService;

    public ProductView(ProductService productService) {
        this.productService = productService;
    }

    public void show(OnBackListener listener) {
        List<Product> products = productService.getProducts();
        System.out.println("---- Available Products ----");
        for (int i = 0; i < products.size(); i++) {
            System.out.println((i + 1) + ". " + products.get(i).getName() + " (@" + products.get(i).getPrice() + ") Stock: " + products.get(i).getStock());
        }
        boolean added = selectProduct(products, listener);
        if (added) {
            String addMore = ScannerUtil.scanText("Add another product? [y/n] : ");
            if ("y".equalsIgnoreCase(addMore)) {
                show(listener);
            } else {
                listener.onBackPressed();
            }
        }
    }

    private boolean selectProduct(List<Product> products, OnBackListener listener) {
        System.out.println("---------------------------");
        System.out.println("[1-" + products.size() + "] Select a product");
        System.out.println("[0] Back to main");
        int input = ScannerUtil.scanIntegerLimited("Select : ", products.size(), "Invalid option");
        if (input == 0) {
            listener.onBackPressed();
            return false;
        }
        Product product = products.get(input - 1);
        if (product.getStock() == 0) {
            System.out.println("Oops, product out of stock!");
            return false;
        }
        int productQty = ScannerUtil.scanIntegerLimited("Enter quantity : ", product.getStock(), "Invalid quantity");
        productService.updateProductStock(product, (productQty * -1));
        CartItem newItem = new CartItem(product, productQty, product.getPrice() * productQty);
        productService.addOrUpdateCartItem(newItem);
        System.out.println("Product successfully added to your cart");
        return true;
    }

}
