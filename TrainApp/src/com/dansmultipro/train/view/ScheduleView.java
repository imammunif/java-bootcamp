package com.dansmultipro.train.view;

import com.dansmultipro.train.listener.OnBackListener;
import com.dansmultipro.train.model.Railcar;
import com.dansmultipro.train.model.SeatRow;
import com.dansmultipro.train.model.Train;
import com.dansmultipro.train.service.TrainService;
import com.dansmultipro.train.util.ScannerUtil;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ScheduleView {

    private final TrainService trainService;
    DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    public ScheduleView(TrainService trainService) {
        this.trainService = trainService;
    }

    public void show(OnBackListener listener) {
        List<Train> trains = trainService.getScedhule();
        System.out.println("\n============ Showing train schedule ============");
        for (int i = 0; i < trains.size(); i++) {
            System.out.println((i + 1) + ". " + trains.get(i).getName() + " (" + trains.get(i).getFrom() + " - " + trains.get(i).getTo() + ") " + trains.get(i).getDateTime().format(timeFormat));
        }
        System.out.println("Options:");
        System.out.println("[1] Show available seats");
        System.out.println("[2] Back");
        int options = ScannerUtil.scanLimitedOption("Select : ", 2);
        if (options == 1) {
            showGetTicket(trains);
        } else if (options == 2) {
            listener.onBackPressed();
        }
        show(listener);
    }

    private void showGetTicket(List<Train> trains) {
        //System.out.println("\n====== Ordering train ticket ======");
        Train train = trains.get(ScannerUtil.scanLimitedOption("Select a train : ", trains.size()) - 1);
        showTrainRailcars(train);
    }

    private void showTrainRailcars(Train train) {
        //  System.out.println("\n------ Available railcars ------");
        System.out.println("\n===== Railcars from " + train.getName() + " =====");
        List<Railcar> railcars = train.getRailcars();
        for (int i = 0; i < railcars.size(); i++) {
            System.out.println((i + 1) + ". " + railcars.get(i).getName());
        }
        System.out.println("Please select railcar to show available seats");
        Railcar railcar = railcars.get(ScannerUtil.scanLimitedOption("Select a railcar: ", railcars.size()) - 1);
        showRailcarSeats(train, railcar);
    }

    private void showRailcarSeats(Train train, Railcar railcar) {
        System.out.println("\n------------ Displaying seats -------------");
        List<SeatRow> seatRows = railcar.getSeatRows();
        int rowCapacity = 0;
        for (SeatRow seatRow : seatRows) {
            System.out.print(seatRow.getName() + " : ");
            Map<Integer, Boolean> seats = seatRow.getSeats();
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

        System.out.println("\n-------------------------------------------");
        System.out.println("Options:");
        System.out.println("[1] Order a ticket");
        System.out.println("[2] Back");
        int options = ScannerUtil.scanLimitedOption("Select : ", 2);
        if (options == 1) {
            showSelectSeat(seatRows);
        } else if (options == 2) {
            return;
        }
        showTrainRailcars(train);
    }

    private void showSelectSeat(List<SeatRow> seatRows) {
        List<String> rowOptions = Arrays.asList("a", "b", "c", "d");
        System.out.println("\n========== Ordering train ticket ==========");
        String selectedRow = ScannerUtil.scanLimitedText("Select row : ", rowOptions);
        Integer selectedSeatNumber = ScannerUtil.scanLimitedOption("Select seat number : ", 10);

        /*
        seatrows = [
            seatrow = [ name = "A", hashmap = {(1,true), (2,false) ..., (10,false)}],
            seatrow = [ name = "B", hashmap = {(1,true), (2,false) ..., (10,false)}],
            seatrow = [ name = "C", hashmap = {(1,true), (2,false) ..., (10,false)}],
            seatrow = [ name = "D", hashmap = {(1,true), (2,false) ..., (10,false)}]
        ]
        */

        for (SeatRow seatRow : seatRows) {
            if (!selectedRow.equalsIgnoreCase(seatRow.getName())) {
                continue;
            }
            Map<Integer, Boolean> seats = seatRow.getSeats();
            for (Map.Entry<Integer, Boolean> entry : seats.entrySet()) {
                if (entry.getKey().equals(selectedSeatNumber) && entry.getValue()) {
                    System.out.println("Oops, seat already booked!");
                    return;
                }
            }
            System.out.println("Train ticket ordered successfully!");
        }
    }
}
