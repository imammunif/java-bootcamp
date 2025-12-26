package com.dansmulti.ojoltwo.service.impl;

import com.dansmulti.ojoltwo.model.Driver;
import com.dansmulti.ojoltwo.service.RideService;

public class RideServiceImpl implements RideService {

    @Override
    public Driver findDriver() {
        return new Driver("Budi", "B 9876 YZ");
    }

    public int calculatePrice(String from, String to) {
        int totalPrice = (from.length() * to.length()) + 10000;
        return totalPrice;
    }
}
