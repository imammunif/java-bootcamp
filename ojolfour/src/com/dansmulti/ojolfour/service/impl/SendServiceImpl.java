package com.dansmulti.ojolfour.service.impl;

import com.dansmulti.ojolfour.model.Driver;
import com.dansmulti.ojolfour.service.SendService;

import java.util.*;

public class SendServiceImpl implements SendService {

    @Override
    public List<String> getCategories() {
        return Arrays.asList("kurir", "cargo", "logistic", "ekspedisi");
    }

    @Override
    public Driver findDriver() {
        List<Driver> drivers = Arrays.asList(
                new Driver("Dono", "B 8485 KX"),
                new Driver("Kasino", "B 9876 YZ"),
                new Driver("Indro", "A 7632 PK"),
                new Driver("Nanu", "D 8542 KT"),
                new Driver("Rudy", "D 3764 HK")
        );
        Random random = new Random();
        return drivers.get(random.nextInt(drivers.size()));
    }

    @Override
    public Double getDiscount(String voucher) {
        Map<String, Double> discounts = new HashMap<>();
        discounts.put("RINGANMURAH12", 12.0);
        discounts.put("PROMO25", 25.0);

        if (discounts.containsKey(voucher)) {
            return discounts.get(voucher);
        }
        return 0.0;
    }

    @Override
    public Double calculateBill(String from, String to, String category, double weight, String voucher) {
        Double discount = getDiscount(voucher);
        Double total = (category.length() * weight) + (from.length() * to.length() + 1000);
        return total - (total * discount / 100.0);
    }


}
