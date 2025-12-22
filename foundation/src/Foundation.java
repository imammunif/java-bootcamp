public class Foundation {

    public static void main(String[] args) {

        // Variables
        String name = "Budi";
        int age = 21;
        System.out.println(name + " is " + age + " years old");

        // Operators
        int number1 = 10;
        int number2 = 200;
        int number3 = 300;

        // Operators - Arithmetic
        int number4 = 10 + 20; //output = 30
        int number5 = 200 - 1; //output = 199
        float number6 = 309 / 2; //output 154.5

        // Operators - Comparison
        boolean result1 = 10 == 11; //output = false
        boolean result2 = 10 > 20; //output = false
        boolean result3 = 10 >= 10; //output = true
        boolean result4 = 10 != 11; //output = true

        // Operators - Logic
        boolean test1 = true;
        boolean test2 = false;
        boolean logic1 = test1 && test2; //output = false
        boolean logic2 = test1 || test2; //output = true
        boolean logic3= !test1; //output = false

        // Operators - Increment Decrement
        int numb1 = 1;
        numb1++;
        // numb1 output is 2
        int numb2 = 2;
        numb2++;
        // numb2 output is 3

        // Control Flow - Conditional if
        int key = 10;
        if ( key < 15 ) {
            // first condition
        } else if ( key >= 20 ) {
            // second condition
        } else {
            // third condition
        }

        // Control Flow - Looping for i
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
        }
        /* HOW TO READ THE LOOPING
            1. initiate -> i = 0
            2. condition -> i < 10 -> boolean
                true -> execute statement print
                false -> terminate
            3. increment/decrement
         */

        // Control Flow - Looping While
        int i = 0;
        while ( i < 10) {
            i++;
        }
    }

}
