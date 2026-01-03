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
            System.out.println((i + 1) + ". " + products.get(i).getName() + " (@" + products.get(i).getPrice() + ") Available: " + products.get(i).getStock());
        }
        Product product = products.get(ScannerUtil.scanLimitedOption("Select a product : ", products.size()) - 1);
        int productQty = ScannerUtil.scanLimitedOption("Enter quantity : ", product.getStock());
        productService.updateProductStock(product, (productQty * -1));
        CartItem newItem = new CartItem(product, productQty, product.getPrice() * productQty);
        productService.addOrUpdateCartItem(newItem);
        String addMore = ScannerUtil.scanText("\nDo you want to add more? [y/n] : ");
        if ("y".equalsIgnoreCase(addMore)) {
            show(listener);
        } else {
            listener.onBackPressed();
        }
    }
}
