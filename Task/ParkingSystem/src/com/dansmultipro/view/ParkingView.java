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
import java.util.stream.Collectors;

public class ParkingView {

    private final ParkingService parkingService;
    private final DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss yyyy-MM-dd");

    public ParkingView(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    public void showCheckIn(OnBackListener listener) {
        List<Parking> parkingList = parkingService.getHistory();
        System.out.println(" --- Checking in ---");
        String firstPart = inputFirstPart();
        int secondPart = inputSecondPart();
        String thirdPart = inputThirdPart();
        String license = firstPart + secondPart + thirdPart;
        System.out.println("[1] Car [2] Motorcycle");
        VehicleType vehicleType = inputVehicleType();
        checkInConfirmation(listener, vehicleType, license, parkingList);
        listener.onBackPressed();
    }

    public void showCheckOut(OnBackListener listener) {
        List<Parking> parkingList = parkingService.getHistory();
        if (parkingList.isEmpty()) {
            System.out.println("No active parking...");
            return;
        }
        System.out.println("--- Checking out ---");
        List<Parking> activeParking = parkingList.stream()
                .filter(parking -> parking.getCheckOutTime() == null)
                .collect(Collectors.toList());
        activeParking.forEach(parking -> System.out.println(
                "ID " + parking.getSequence() +
                " License " + parking.getLicence() +
                " Type " + parking.getType().getLabel() +
                " Check in " + parking.getCheckInTime().format(timeFormat) +
                " Check out " + (parking.getCheckOutTime() == null ? "-" : parking.getCheckOutTime().format(timeFormat))
        ));
        String license = ScannerUtil.scanText("Input license to checkout: ").trim();
        Parking parkingToCheckout = activeParking.stream()
                .filter(parking -> parking.getLicence().equalsIgnoreCase(license))
                .findFirst()
                .orElse(null);
        if (parkingToCheckout == null) {
            System.out.println("Oops, license not found!");
            return;
        }
        parkingService.checkoutParking(parkingToCheckout);
        parkingService.calculateBill(parkingToCheckout);
        System.out.println("Successfully checking out, thanks...");
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
        return ScannerUtil.scanLimitedOption("Input the main number : ", 99999);
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

    private void checkInConfirmation(OnBackListener listener, VehicleType vehicleType, String license, List<Parking> parkingList) {
        String checkoutApproval = ScannerUtil.scanText("Are you sure you want check in? [y/n] : ");
        if ("y".equalsIgnoreCase(checkoutApproval)) {
            Parking newParking = new Parking(RandomSequence.getAlphaNumericString(8), vehicleType, license, LocalDateTime.now().minusMinutes(75), null, 0d);
            if (parkingService.isExistLicenseCheckout(parkingList, newParking)) {
                System.out.println("Oops, the license you input isn't checking out yet");
                return;
            }
            parkingService.setParkingHistory(newParking);
            return;
        }
        listener.onBackPressed();
    }
}
