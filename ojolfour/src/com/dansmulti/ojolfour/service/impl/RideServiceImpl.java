package com.dansmulti.ojolfour.service.impl;

import com.dansmulti.ojolfour.model.Driver;
import com.dansmulti.ojolfour.service.RideService;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

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

    public int calculatePrice(String from, String to) {
        int totalPrice = (from.length() * to.length()) + 10000;
        return totalPrice;
    }
}
