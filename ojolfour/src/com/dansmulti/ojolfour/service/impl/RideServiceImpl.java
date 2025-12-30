package com.dansmulti.ojolfour.service.impl;

import com.dansmulti.ojolfour.model.Driver;
import com.dansmulti.ojolfour.service.RideService;

import java.util.*;

public class RideServiceImpl implements RideService {

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
    public Double calculatePrice(String voucher, String from, String to) {
        Double discount = getDiscount(voucher);
        Double totalPrice = Double.valueOf((from.length() * to.length() + 10000));
        return totalPrice - (totalPrice * discount / 100.0);
    }

    @Override
    public Double getDiscount(String voucher) {
        Map<String, Double> discounts = new HashMap<>();
        discounts.put("DECEMBER12", 12.0);
        discounts.put("AKHIRTAHUN25", 25.0);

        if (discounts.containsKey(voucher)) {
            return discounts.get(voucher);
        }
        return 0.0;
    }
}
