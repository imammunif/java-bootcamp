import java.util.Arrays;

public class Foundation2 {

    // 01 ########### method #############
    public void printVoid() {
        String result = printAndReturn();
        System.out.println(result);
    }

    // Jika nilainya mau dipakai, maka pakai non void
    public String printAndReturn() {
        printVoid();
        return "data";
    }

    public static void main (String[] args) {
        // 02 ########### Array 1 dimension #############
        int[] dataInt = new int[3];

        // a. assigning later
        dataInt[0] = 1;
        dataInt[1] = 2;
        dataInt[2] = 3;

        // combined with for loop
        for ( int i = 0; i < dataInt.length; i++) {
            System.out.println("first method");
            System.out.println(dataInt[i]);
        }

        // simpler term of for loop
        for(int number : dataInt) {
            System.out.println("second method");
            System.out.println(number);
        }

        // b. assigning now
        int[] dataInteger = {100, 200, 300};

        // 03 ########### Array 2 dimension #############
        int[][] array2d = new int[2][3];
        array2d[0][1] = 1;
        array2d[0][2] = 2;
        array2d[0][3] = 3;
        array2d[1][1] = 4;
        array2d[1][2] = 5;
        array2d[1][3] = 6;

        int[][] array2d2 = {
            {1,2,3},
            {4,5,6}
        };

        // 04 ########### Tipe data non primitive #############
        String name1 = "budi";
        String name2 = "Budi";

        if (name1. equals(name2)) {
            //statement
        }

        // System.out.println(Arrays.toString(dataInt));
    }
}
