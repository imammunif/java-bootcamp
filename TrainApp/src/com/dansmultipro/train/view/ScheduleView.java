package com.dansmultipro.train.view;

import com.dansmultipro.train.model.Railcar;
import com.dansmultipro.train.model.SeatRow;
import com.dansmultipro.train.model.Train;
import com.dansmultipro.train.service.TrainService;
import com.dansmultipro.train.util.ScannerUtil;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ScheduleView {

    private final TrainService trainService;
    DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    public ScheduleView(TrainService trainService) {
        this.trainService = trainService;
    }

    public void show() {
        System.out.println("\n====== Train schedule ======");
        showTrains();
    }

    private void showTrains() {
        List<Train> trains = trainService.getScedhule();
        for (int i = 0; i < trains.size(); i++) {
            System.out.println((i + 1) + ". " + trains.get(i).getName() + " (" + trains.get(i).getFrom() + " - " + trains.get(i).getTo() + ") " + trains.get(i).getDateTime().format(timeFormat));
        }
        System.out.println("Options:");
        System.out.println("[1] Get Ticket");
        System.out.println("[0] Back");
        int options = ScannerUtil.scanLimitedOption("Select : ", 2);
        if (options == 1) {
            showGetTicket(trains);
        } else if (options == 0) {
            return;
        }
        show();
    }

    private void showGetTicket(List<Train> trains) {
        System.out.println("\n====== Ordering train ticket ======");
        Train train = trains.get(ScannerUtil.scanLimitedOption("Select a train : ", trains.size()) - 1);
        showTrainRailcars(train);
    }

    private void showTrainRailcars(Train train) {
        //  System.out.println("\n------ Available railcars ------");
        System.out.println("Showing available railcars from " + train.getName() + " ...");
        List<Railcar> railcars = train.getRailcars();
        for (int i = 0; i < railcars.size(); i++) {
            System.out.println((i + 1) + ". " + railcars.get(i).getName());
        }
        Railcar railcar = railcars.get(ScannerUtil.scanLimitedOption("Select a railcar : ", railcars.size()) - 1);
        showRailcarSeats(railcar);
    }

    private void showRailcarSeats(Railcar railcar) {
        System.out.println("\n----------- Displaying seats -----------");
        List<SeatRow> seatRows = railcar.getSeatRows();
        Map<Integer, Boolean> seats;
        int rowCapacity = 0;
        for (SeatRow seatRow : seatRows) {
            System.out.print(seatRow.getName() + " : ");
            seats = seatRow.getSeats();
            rowCapacity = seats.size();
            for (Map.Entry<Integer, Boolean> set : seats.entrySet()) {
                if (set.getValue()) {
                    System.out.print("[X] ");
                } else {
                    System.out.print("[ ] ");
                }
            }
            System.out.println();
        }
        System.out.print("    ");
        for (int i = 0; i < rowCapacity; i++) {
            System.out.print(" " + (i + 1) + "  ");
        }

        showSelectSeat(rowCapacity);



//        if selectseat == true {
//            print already booked
//        } else {
//            update seat value
//        }
    }

    private void showSelectSeat(int rowCapacity) {
        List<String> rowOptions = Arrays.asList("a","b","c","d");
        String selectedRow = ScannerUtil.scanLimitedText("\nSelect row : ", rowOptions);
        Integer selectedSeatNumber = ScannerUtil.scanLimitedOption("Select seat number : ", rowCapacity);
    }
}
