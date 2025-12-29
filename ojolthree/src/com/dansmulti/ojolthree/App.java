package com.dansmulti.ojolthree;

import com.dansmulti.ojolthree.service.FoodService;
import com.dansmulti.ojolthree.service.RideService;
import com.dansmulti.ojolthree.service.SendService;
import com.dansmulti.ojolthree.service.impl.FoodServiceImpl;
import com.dansmulti.ojolthree.service.impl.RideServiceImpl;
import com.dansmulti.ojolthree.service.impl.SendServiceImpl;
import com.dansmulti.ojolthree.view.FoodView;
import com.dansmulti.ojolthree.view.MainView;
import com.dansmulti.ojolthree.view.RideView;
import com.dansmulti.ojolthree.view.SendView;

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
