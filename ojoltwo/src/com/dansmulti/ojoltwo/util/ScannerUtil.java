package com.dansmulti.ojoltwo.util;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ScannerUtil {

    public static int scanOptionNumber(String message, int limit) {
        System.out.print(message);
        Scanner scanner = new Scanner(System.in);
        try {
            int option = scanner.nextInt();

            if (option >= 1 && option <= limit) {
                return option;
            } else {
                System.out.println("Invalid range");
                return scanOptionNumber(message, limit);
            }
        } catch (InputMismatchException ime) {
            System.out.println("Invalid input");
            return scanOptionNumber(message, limit);
        }
    }

    public static double scanDouble(String message) {
        System.out.print(message);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextDouble();
    }

    public static String scanText(String message) {
        System.out.print(message);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
