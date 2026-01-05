package com.dansmultipro.view;

import com.dansmultipro.constant.VehicleType;
import com.dansmultipro.listener.OnBackListener;
import com.dansmultipro.model.Parking;
import com.dansmultipro.service.ParkingService;
import com.dansmultipro.util.RandomSequence;
import com.dansmultipro.util.ScannerUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ParkingView {

    private final ParkingService parkingService;
    private final DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss yyyy-MM-dd");

    public ParkingView(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    public void show(OnBackListener listener) {
        listener.onBackPressed();
    }

    public void showCheckIn(OnBackListener listener) {
        System.out.println(" --- Checking in ---");
        String firstPart = inputFirstPart();
        int secondPart = inputSecondPart();
        String thirdPart = inputThirdPart();
        String license = firstPart + secondPart + thirdPart;
        System.out.println("[1] Car [2] Motorcycle");
        VehicleType vehicleType = inputVehicleType();
        checkInConfirmation(listener, vehicleType, license);
        listener.onBackPressed();
    }

    public void showCheckOut(OnBackListener listener) {
        List<Parking> parkingList = parkingService.getHistory();
        System.out.println(" --- Checking out ---");
        for (Parking parking : parkingList) {
            if (parking.isCheckOut() == false) {
                System.out.println("ID " + parking.getSequence() + " License " + parking.getLicence() + " Check in " + parking.getCheckInTime().format(timeFormat) + " Check out " + parking.getCheckOutTime().format(timeFormat));
            }
        }
        listener.onBackPressed();
    }

    private String inputFirstPart() {
        String firstPart = ScannerUtil.scanText("Input the city code : ");
        if (!"b".equalsIgnoreCase(firstPart)) {
            System.out.println("Sorry, only accepting from Jakarta area (City code \"B\")");
            inputFirstPart();
        }
        return firstPart;
    }

    private int inputSecondPart() {
        return ScannerUtil.scanInt("Input the main number : ");
    }

    private String inputThirdPart() {
        return ScannerUtil.scanText("Input the last code : ");
    }

    private VehicleType inputVehicleType() {
        int option = ScannerUtil.scanLimitedOption("Select vehicle type [1-2]: ", 2);
        if (option == 1) {
            return VehicleType.CAR;
        }
        return VehicleType.MOTORCYCLE;
    }

    private void checkInConfirmation(OnBackListener listener, VehicleType vehicleType, String license) {
        String checkoutApproval = ScannerUtil.scanText("Are you sure you want check in? [y/n] : ");
        if ("y".equalsIgnoreCase(checkoutApproval)) {
            Parking newParking = new Parking(RandomSequence.getAlphaNumericString(8), vehicleType, license, true, false, LocalDateTime.now(), null, 0d);
            parkingService.setParkingHistory(newParking);
            return;
        }
        listener.onBackPressed();
    }
}
