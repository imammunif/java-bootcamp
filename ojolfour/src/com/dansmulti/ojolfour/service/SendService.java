package com.dansmulti.ojolfour.service;

import com.dansmulti.ojolfour.model.Driver;

import java.util.List;

public interface SendService {

    List<String> getCategories();

    Driver findDriver();

    Double getDiscount(String voucher);

    Double calculateBill(String from, String to, String category, double weight, String voucher);

}
