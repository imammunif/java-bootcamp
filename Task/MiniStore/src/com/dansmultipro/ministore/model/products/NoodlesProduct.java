package com.dansmultipro.ministore.model.products;

import com.dansmultipro.ministore.model.Product;
import com.dansmultipro.ministore.model.constant.ProductType;

public class NoodlesProduct extends Product {

    public NoodlesProduct(ProductType type, String name, double price, int stock) {
        super(type, name, price, stock);
    }

}