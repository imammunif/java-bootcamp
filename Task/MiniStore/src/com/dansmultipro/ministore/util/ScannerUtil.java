package com.dansmultipro.ministore.util;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ScannerUtil {
    public static int scanLimitedOption(String message, int limit, String response) {
        System.out.print(message);
        Scanner scanner = new Scanner(System.in);
        try {
            int option = scanner.nextInt();

            if (option >= 0 && option <= limit) {
                return option;
            } else {
                System.out.println(response);
                return scanLimitedOption(message, limit, response);
            }
        } catch (InputMismatchException ime) {
            System.out.println("Invalid input");
            return scanLimitedOption(message, limit, response);
        }
    }

    public static int scanInt(String message) {
        System.out.print(message);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public static String scanText(String message) {
        System.out.print(message);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
