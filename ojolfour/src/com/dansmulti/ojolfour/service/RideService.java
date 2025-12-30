package com.dansmulti.ojolfour.service;

import com.dansmulti.ojolfour.model.Driver;

public interface RideService {

    Driver findDriver();

    int calculatePrice(String from, String to);
}
