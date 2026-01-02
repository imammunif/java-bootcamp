package com.dansmultipro.ministore.service.impl;

import com.dansmultipro.ministore.model.Product;
import com.dansmultipro.ministore.model.constant.ProductType;
import com.dansmultipro.ministore.model.products.FruitsProduct;
import com.dansmultipro.ministore.model.products.NoodlesProduct;
import com.dansmultipro.ministore.model.products.VeggiesProduct;
import com.dansmultipro.ministore.model.products.WaterProduct;
import com.dansmultipro.ministore.service.ProductService;

import java.util.Arrays;
import java.util.List;

public class ProductServiceImpl implements ProductService {
    @Override
    public List<Product> getProducts() {
        List<Product> products = Arrays.asList(
                new FruitsProduct(ProductType.FRUIT, "Nanas", 16000, 20),
                new FruitsProduct(ProductType.FRUIT, "Pepaya", 14000, 40),
                new VeggiesProduct(ProductType.VEGGIE, "Bayam", 10000, 80),
                new VeggiesProduct(ProductType.VEGGIE, "Brokoli", 8000, 50),
                new NoodlesProduct(ProductType.NOODLE, "Indomie", 3000, 500),
                new NoodlesProduct(ProductType.NOODLE, "Mie Sedap", 3000, 400),
                new WaterProduct(ProductType.WATER, "Danone", 3500, 300),
                new WaterProduct(ProductType.WATER, "Le Minerale", 3500, 400)
        );
        return products;
    }
}
