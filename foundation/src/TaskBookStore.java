import java.util.Scanner;

public class TaskBookStore {

    public static void main(String[] args) {
        new TaskBookStore().mainMenu();
    }

    public void mainMenu() {

        Scanner scanner = new Scanner(System.in);
        String[] arrBooks = {"Laskar Pelangi", "Kelinci vs Kura-kura", "Marmut Merah Jambu", "Mars vs Venus", "Jurus cepat masuk CPNS"};
        double[] arrPrices = {20000, 50000, 35000, 10000, 43000};

        System.out.println("\n=== Book Store ===");
        System.out.println(" 1. View Book");
        System.out.println(" 2. Buy Book");
        System.out.println(" 3. Exit");
        System.out.print("Pilih [1-3] : ");
        int optionInput = scanner.nextInt();

        if (optionInput == 1) {
            viewBook(arrBooks, arrPrices);
        } else if (optionInput == 2) {
            buyBook(arrBooks, arrPrices);
        } else if (optionInput == 3) {
            System.out.println("Thanks");
        } else {
            System.out.println("Invalid input");
            mainMenu();
        }
    }

    public void viewBook(String[] arrBooks, double[] arrPrices) {

        System.out.println("=== View Book ===\n");
        for (int i = 0; i < arrBooks.length; i++) {
            System.out.println((i+1) + ". " + arrBooks[i] + " (@" + arrPrices[i] + ")");
        }
        mainMenu();
    }

    public void buyBook(String[] arrBooks, double[] arrPrices) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Pilih Buku : ");
        int selected = scanner.nextInt();
        System.out.print("Masukkan jumlah beli : ");
        int qty = scanner.nextInt();
        double total = arrPrices[selected - 1] * qty;
        System.out.printf("Anda membeli buku " + arrBooks[selected - 1] + " sebanyak " + qty + " dengan total " + total);
        mainMenu();
    }

}
