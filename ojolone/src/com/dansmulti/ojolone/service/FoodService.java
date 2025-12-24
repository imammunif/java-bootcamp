package com.dansmulti.ojolone.service;

import com.dansmulti.ojolone.model.Driver;
import com.dansmulti.ojolone.model.Restaurant;

public interface FoodService {

    // Abstract methods
    // soon will be override by the Implementation class
    Driver findDriver();

    double calculateBill(String from, String to, int qty, double price);

    Restaurant[] getRestaurants();
}
