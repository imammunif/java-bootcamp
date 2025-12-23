package com.dansmulti.ojolone.service.impl;

import com.dansmulti.ojolone.model.Driver;
import com.dansmulti.ojolone.service.SendService;

public class SendServiceImpl implements SendService {

    @Override
    public Driver findDriver() {
        return new Driver("Budi", "B 9876 YZ");
    }

    @Override
    public double calculatePrice(String from, String to, String category, double weight) {

          System.out.println("(ctg length " + category.length() + " * weight "+ weight + ") = " + category.length() * weight);
//          System.out.println(from.length() * to.length() + 1000);

        double totalPrice = (
                (category.length() * weight) +
                (from.length() * to.length() + 1000)
        );
        return totalPrice;
    }

    @Override
    public String[] getCategories() {
        return new String[]{"kurir", "cargo", "logistic", "ekspedisi"};
    }

}
