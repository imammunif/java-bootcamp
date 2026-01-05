package com.dansmultipro.service;

import com.dansmultipro.model.Parking;

import java.util.List;

public interface ParkingService {

    Boolean isExistLicenseCheckout(List<Parking> parkingList, Parking newParking);

    void checkoutParking(Parking newParking);

    List<Parking> getHistory();

    void setParkingHistory(Parking newParking);
}
