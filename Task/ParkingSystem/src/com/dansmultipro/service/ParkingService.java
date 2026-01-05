package com.dansmultipro.service;

import com.dansmultipro.model.Parking;

import java.util.List;

public interface ParkingService {

    void setLicense();

    void setCheckIn();

    void setCheckOut();

    void setCheckInTime();

    void setCheckOutTime();

    void setGrandTotal();

    List<Parking> getHistory();

    void setParkingHistory(Parking newParking);
}
