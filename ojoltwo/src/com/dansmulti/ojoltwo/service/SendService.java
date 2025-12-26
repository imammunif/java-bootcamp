package com.dansmulti.ojoltwo.service;

import com.dansmulti.ojoltwo.model.Driver;

import java.util.List;

public interface SendService {

    Driver findDriver();

    double calculatePrice(String from, String to, String category, double weight);

    List<String> getCategories();
}
