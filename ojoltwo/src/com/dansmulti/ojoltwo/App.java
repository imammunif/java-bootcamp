package com.dansmulti.ojoltwo;

import com.dansmulti.ojoltwo.service.FoodService;
import com.dansmulti.ojoltwo.service.RideService;
import com.dansmulti.ojoltwo.service.SendService;
import com.dansmulti.ojoltwo.service.impl.FoodServiceImpl;
import com.dansmulti.ojoltwo.service.impl.RideServiceImpl;
import com.dansmulti.ojoltwo.service.impl.SendServiceImpl;
import com.dansmulti.ojoltwo.view.FoodView;
import com.dansmulti.ojoltwo.view.MainView;
import com.dansmulti.ojoltwo.view.RideView;
import com.dansmulti.ojoltwo.view.SendView;

public class App {
    public static void main(String[] args) {

        RideService rideService = new RideServiceImpl();
        SendService sendService = new SendServiceImpl();
        FoodService foodService = new FoodServiceImpl();

        RideView rideView = new RideView(rideService);
        SendView sendView = new SendView(sendService);
        FoodView foodView = new FoodView(foodService);

        new MainView(rideView, sendView, foodView).show();

    }
}
