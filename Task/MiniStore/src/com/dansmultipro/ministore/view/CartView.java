package com.dansmultipro.ministore.view;

import com.dansmultipro.ministore.listener.OnBackListener;
import com.dansmultipro.ministore.service.ProductService;

public class CartView {

    private final ProductService productService;

    public CartView(ProductService productService) {
        this.productService = productService;
    }

    public void show(OnBackListener listener) {
        listener.onBackPressed();
    }
}
