public class Person extends BasePerson {

    private int age;

    public Person() {
        System.out.println("constructor: exec 2nd");
    }

    @Override
    public void test() {

    }

    public void callTest() {
        int age = 10;
        int result1 = age;
        int result2 = this.age;

        test(); // call the closest, else: look in parent
        this.test(); // call the method in the same class
        super.test(); // call the first parent method
    }

}
