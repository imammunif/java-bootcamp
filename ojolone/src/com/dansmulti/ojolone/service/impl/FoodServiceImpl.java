package com.dansmulti.ojolone.service.impl;

import com.dansmulti.ojolone.model.Driver;
import com.dansmulti.ojolone.model.Menu;
import com.dansmulti.ojolone.model.Restaurant;
import com.dansmulti.ojolone.service.FoodService;

public class FoodServiceImpl implements FoodService {

    @Override
    public Driver findDriver() {
        return new Driver("Budi", "B 9876 YZ");
    }

    @Override
    public double calculatePrice(String from, String to, int qty, double price) {

        double totalPrice = ((qty * price) + (from.length() * to.length() + 1000));
        return totalPrice;
    }

    @Override
    public Restaurant[] getRestaurants() {
        return new Restaurant[]{
                new Restaurant("Duta Minang", "Jl. Merdeka, DKI", new Menu[]{
                        new Menu("Nasi padang", 20000),
                        new Menu("Nasi rendang", 25000),
                }),
                new Restaurant("Gacoan", "Jl. Merpati, Depok", new Menu[]{
                        new Menu("Mi setan", 18000),
                        new Menu("Mi iblis", 18000)
                }),
                new Restaurant("Nasi Kulit Syurga", "Jl. Mawar, Bekasi", new Menu[]{
                        new Menu("Nasi kulit sambal matah", 25000),
                        new Menu("Nasi kulit sambal bawang", 25000),
                })
        };
    }

}
