package com.dansmulti.ojolone;

import com.dansmulti.ojolone.service.FoodService;
import com.dansmulti.ojolone.service.RideService;
import com.dansmulti.ojolone.service.SendService;
import com.dansmulti.ojolone.service.impl.FoodServiceImpl;
import com.dansmulti.ojolone.service.impl.RideServiceImpl;
import com.dansmulti.ojolone.service.impl.SendServiceImpl;
import com.dansmulti.ojolone.view.FoodView;
import com.dansmulti.ojolone.view.MainView;
import com.dansmulti.ojolone.view.RideView;
import com.dansmulti.ojolone.view.SendView;
import com.sun.tools.javac.Main;

public class App {
    public static void main(String[] args) {
        // Instance the interface abstract class with the interface implementation
        RideService rideService = new RideServiceImpl();
        SendService sendService = new SendServiceImpl();
        FoodService foodService = new FoodServiceImpl();
        // Instance the view with the service
        RideView rideView = new RideView(rideService);
        SendView sendView = new SendView(sendService);
        FoodView foodView = new FoodView(foodService);
        new MainView(rideView, sendView, foodView).show();

    }
}
