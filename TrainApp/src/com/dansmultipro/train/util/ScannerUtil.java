package com.dansmultipro.train.util;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ScannerUtil {
    public static int scanLimitedOption(String message, int limit) {
        System.out.print(message);
        Scanner scanner = new Scanner(System.in);
        try {
            int option = scanner.nextInt();

            if (option >= 1 && option <= limit) {
                return option;
            } else {
                System.out.println("Invalid range");
                return scanLimitedOption(message, limit);
            }
        } catch (InputMismatchException ime) {
            System.out.println("Invalid input");
            return scanLimitedOption(message, limit);
        }
    }

    public static String scanLimitedText(String message, List<String> options) {
        System.out.print(message);
        Scanner scanner = new Scanner(System.in);
        try {
            String input = scanner.nextLine().trim().toLowerCase();
            if (options.contains(input)) {
                return input;
            } else {
                System.out.println("Invalid range");
                return scanLimitedText(message, options);
            }
        } catch (Exception e) {
            System.out.println("Invalid input");
            return scanLimitedText(message, options);
        }
    }

    public static int scanInt(String message) {
        System.out.print(message);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public static double scanDouble(String message) {
        System.out.print(message);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextDouble();
    }
}
