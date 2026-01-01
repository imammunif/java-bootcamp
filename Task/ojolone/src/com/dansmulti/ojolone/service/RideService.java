package com.dansmulti.ojolone.service;

import com.dansmulti.ojolone.model.Driver;

public interface RideService {

    Driver findDriver();

    int calculatePrice(String from, String to);
}
