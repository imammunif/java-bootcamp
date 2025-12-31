package com.dansmulti.ojolfour;

import com.dansmulti.ojolfour.service.FoodService;
import com.dansmulti.ojolfour.service.HistoryService;
import com.dansmulti.ojolfour.service.RideService;
import com.dansmulti.ojolfour.service.SendService;
import com.dansmulti.ojolfour.service.impl.FoodServiceImpl;
import com.dansmulti.ojolfour.service.impl.HistoryServiceImpl;
import com.dansmulti.ojolfour.service.impl.RideServiceImpl;
import com.dansmulti.ojolfour.service.impl.SendServiceImpl;
import com.dansmulti.ojolfour.view.*;

public class App {

    public static void main(String[] args) {

        RideService rideService = new RideServiceImpl();
        SendService sendService = new SendServiceImpl();
        FoodService foodService = new FoodServiceImpl();
        HistoryService historyService = new HistoryServiceImpl();

        RideView rideView = new RideView(rideService, historyService);
        SendView sendView = new SendView(sendService, historyService);
        FoodView foodView = new FoodView(foodService, historyService);
        HistoryView historyView = new HistoryView(historyService);

        new MainView(rideView, sendView, foodView, historyView).show();

    }
}