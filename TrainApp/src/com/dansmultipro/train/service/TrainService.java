package com.dansmultipro.train.service;

import com.dansmultipro.train.model.Train;

import java.util.List;

public interface TrainService {

    List<Train> getScedhule();

    Double getDiscount(String voucher);

    Double calculateBill(String from, String to, String voucher);
}
