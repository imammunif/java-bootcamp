package com.dansmulti.ojolfour;

import com.dansmulti.ojolfour.service.FoodService;
import com.dansmulti.ojolfour.service.RideService;
import com.dansmulti.ojolfour.service.SendService;
import com.dansmulti.ojolfour.service.impl.FoodServiceImpl;
import com.dansmulti.ojolfour.service.impl.RideServiceImpl;
import com.dansmulti.ojolfour.service.impl.SendServiceImpl;
import com.dansmulti.ojolfour.view.FoodView;
import com.dansmulti.ojolfour.view.MainView;
import com.dansmulti.ojolfour.view.RideView;
import com.dansmulti.ojolfour.view.SendView;

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
