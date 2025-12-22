import java.util.Scanner;

public class TaskBookStore {

    public static void main(String[] args) {
        /* EXPECTED RESULT
            === Book Store ===
            1. View Book
            2. Buy Book
            3. Exit
            Pilih [1-3] : 1
            === View Book ===
            1. Java @20.000
            2. Spring @50.000

            === Book Store ===
            1. View Book
            2. Buy Book
            3. Exit
            Pilih [1-3] : 2
            Pilih Buku [1-2] : 2
            Masukkan jumlah beli : 2
            === Detail ===
            Anda membeli buku Spring @50.000 sebanyak 2 dengan total Rp100.000

            === Book Store ===
            1. View Book
            2. Buy Book
            3. Exit
            Pilih [1-3] : 3
            === Thanks ===
         */

        TaskBookStore store = new TaskBookStore();
        store.mainMenu();

    }

    public void mainMenu() {

        Scanner scanner = new Scanner(System.in);
        String[] arrBooks = {"Laskar Pelangi", "Kelinci vs Kura-kura", "Marmut Merah Jambu", "Mars vs Venus", "Jurus cepat masuk CPNS"};
        double[] arrPrices = {20000, 50000, 35000, 10000, 43000};
        int optionInput;

        System.out.println("\n=== Book Store ===");
        System.out.println(" 1. View Book");
        System.out.println(" 2. Buy Book");
        System.out.println(" 3. Exit");
        System.out.print("Pilih [1-3] : ");
        optionInput = scanner.nextInt();

        if (optionInput == 1) {
            viewBook(arrBooks, arrPrices);
        } else if (optionInput == 2) {
            buyBook(arrBooks, arrPrices);
        } else if (optionInput == 3) {
            exitStore();
        } else {
            System.out.println("Invalid input");
            mainMenu();
        }
    }

    public void viewBook(String[] arrBooks, double[] arrPrices) {

        System.out.println("=== View Book ===\n");
        for (int i = 0; i < arrBooks.length; i++) {
            int number = i + 1;
            System.out.println(number + ". " + arrBooks[i] + " (@" + arrPrices[i] + ")");
        }
        mainMenu();
    }

    public void buyBook(String[] arrBooks, double[] arrPrices) {
        Scanner scanner = new Scanner(System.in);
        int selected;
        int qty;
        double total;
        System.out.print("Pilih Buku : ");
        selected = scanner.nextInt();
        System.out.print("Masukkan jumlah beli : ");
        qty = scanner.nextInt();
        total = arrPrices[selected - 1] * qty;
        System.out.printf("Anda membeli buku " + arrBooks[selected - 1] + " sebanyak " + qty + " dengan total " + total);
        mainMenu();
    }

    public void exitStore() {
        System.out.println("Thanks");
    }
}
