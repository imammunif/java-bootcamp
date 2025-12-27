package com.dansmulti.ojoltwo.service;

import com.dansmulti.ojoltwo.model.Driver;
import com.dansmulti.ojoltwo.model.Restaurant;

import java.util.List;

public interface FoodService {

    Driver findDriver();

    double calculateBill(String from, String to, double grandTotalCart);

    List<Restaurant> getRestaurants();
}
