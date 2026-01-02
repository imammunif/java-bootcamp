package com.dansmultipro.ministore.model.products;

import com.dansmultipro.ministore.model.Product;
import com.dansmultipro.ministore.model.constant.ProductType;

public class VeggiesProduct extends Product {

    public VeggiesProduct(ProductType type, String name, double price, int stock) {
        super(type, name, price, stock);
    }

}