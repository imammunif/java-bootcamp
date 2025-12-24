package com.dansmulti.ojolone.service.impl;

import com.dansmulti.ojolone.model.Driver;
import com.dansmulti.ojolone.service.SendService;

import java.util.Arrays;
import java.util.List;

public class SendServiceImpl implements SendService {

    @Override
    public Driver findDriver() {
        return new Driver("Budi", "B 9876 YZ");
    }

    @Override
    public double calculatePrice(String from, String to, String category, double weight) {

        double totalPrice = ((category.length() * weight) + (from.length() * to.length() + 1000));
        return totalPrice;
    }

    @Override
    public List<String> getCategories() {
        return Arrays.asList("kurir", "cargo", "logistic", "ekspedisi");
    }

}
