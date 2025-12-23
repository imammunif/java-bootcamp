package com.dansmulti.ojolone.service;

import com.dansmulti.ojolone.model.Driver;

public interface RideService {

    // Abstract methods
    // soon will be override by the Implementation class
    Driver findDriver();

    int calculatePrice(String from, String to);
}
