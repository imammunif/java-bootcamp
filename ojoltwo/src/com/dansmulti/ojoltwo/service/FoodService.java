package com.dansmulti.ojoltwo.service;

import com.dansmulti.ojoltwo.model.Driver;
import com.dansmulti.ojoltwo.model.Restaurant;

import java.util.List;

public interface FoodService {

    // Abstract methods
    // soon will be override by the Implementation class
    Driver findDriver();

    double calculateBill(String from, String to, int qty, double price);

    List<Restaurant> getRestaurants();
}
