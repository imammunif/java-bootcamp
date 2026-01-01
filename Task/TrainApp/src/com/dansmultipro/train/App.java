package com.dansmultipro.train;

import com.dansmultipro.train.service.TrainService;
import com.dansmultipro.train.service.impl.TrainServiceImpl;
import com.dansmultipro.train.view.HistoryView;
import com.dansmultipro.train.view.MainView;
import com.dansmultipro.train.view.ScheduleView;

public class App {
    public static void main(String[] args) {

        TrainService trainService = new TrainServiceImpl();

        ScheduleView scheduleView = new ScheduleView(trainService);
        HistoryView historyView = new HistoryView();

        new MainView(scheduleView, historyView).show();
    }
}