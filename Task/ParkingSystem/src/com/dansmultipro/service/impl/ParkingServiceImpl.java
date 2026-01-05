package com.dansmultipro.service.impl;

import com.dansmultipro.model.Parking;
import com.dansmultipro.service.ParkingService;

import java.util.ArrayList;
import java.util.List;

public class ParkingServiceImpl implements ParkingService {

    private List<Parking> parkingList = new ArrayList<>();

    @Override
    public List<Parking> getHistory() {
        return parkingList;
    }

    @Override
    public void setParkingHistory(Parking newParking) {
        parkingList.add(newParking);
    }

    @Override
    public void setLicense() {

    }

    @Override
    public void setCheckIn() {

    }

    @Override
    public void setCheckOut() {

    }

    @Override
    public void setCheckInTime() {

    }

    @Override
    public void setCheckOutTime() {

    }

    @Override
    public void setGrandTotal() {

    }

}
