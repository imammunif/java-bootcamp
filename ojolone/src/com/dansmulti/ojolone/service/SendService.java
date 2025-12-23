package com.dansmulti.ojolone.service;

import com.dansmulti.ojolone.model.Driver;

public interface SendService {

    // Abstract methods
    // soon will be override by the Implementation class
    Driver findDriver();

    double calculatePrice(String from, String to, String category, double weight);

    String[] getCategories();
}
