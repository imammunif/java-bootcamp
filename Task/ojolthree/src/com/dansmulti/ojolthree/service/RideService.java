package com.dansmulti.ojolthree.service;

import com.dansmulti.ojolthree.model.Driver;

public interface RideService {

    Driver findDriver();

    int calculatePrice(String from, String to);
}
