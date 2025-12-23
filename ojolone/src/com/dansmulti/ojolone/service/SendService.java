package com.dansmulti.ojolone.service;

import com.dansmulti.ojolone.model.Driver;

public interface SendService {

    Driver findDriver();

    double calculatePrice(String from, String to, String category, double weight);

    String[] getCategories();
}
