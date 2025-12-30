package com.dansmulti.ojolfour.service;

import com.dansmulti.ojolfour.model.Driver;

public interface RideService {

    Driver findDriver();

    Double getDiscount(String voucher);

    Double calculatePrice(String voucher, String from, String to);
}
