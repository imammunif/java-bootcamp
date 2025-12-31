package com.dansmultipro.train.view;

import com.dansmultipro.train.model.History;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainView {

    private final ScheduleView scheduleView;
    private final HistoryView historyView;
    private List<History> historyList = new ArrayList<>();

    public MainView(ScheduleView scheduleView, HistoryView historyView) {
        this.scheduleView = scheduleView;
        this.historyView = historyView;
    }

    public void show() {
        System.out.println("=== Train App ====");
        System.out.println("[1] Show schedule");
        System.out.println("[2] Show history");
        System.out.println("[3] Exit");
        System.out.print("Choose [1-3] : ");

        Scanner scanner = new Scanner(System.in);
        int chosen = scanner.nextInt();
        if (chosen == 1) {
            scheduleView.show();
        } else if (chosen == 2) {
            historyView.show();
        } else if (chosen == 3) {
            return;
        }

        show();
    }
}
