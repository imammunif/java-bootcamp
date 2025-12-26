package com.dansmulti.ojoltwo.service;

import com.dansmulti.ojoltwo.model.Driver;

public interface RideService {

    Driver findDriver();

    int calculatePrice(String from, String to);
}
