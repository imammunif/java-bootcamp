package com.dansmulti.ojolone;

import com.dansmulti.ojolone.service.RideService;
import com.dansmulti.ojolone.service.impl.RideServiceImpl;
import com.dansmulti.ojolone.view.MainView;
import com.dansmulti.ojolone.view.RideView;

public class App {
    public static void main(String[] args) {
        // Instance the interface abstract class with the interface implementation
        RideService rideService = new RideServiceImpl();
        // Instance the view with the service
        RideView rideView = new RideView(rideService);
        new MainView(rideView).show();

    }
}
