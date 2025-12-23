public class Person extends BasePerson {

    public Person() {
        System.out.println("constructor: exec 2nd");
    }

    @Override
    public void test() {

    }

    public void callTest() {
        test(); // call the closest, else: look in parent
        this.test(); // call the method in the same class
        super.test(); // call the first parent method
    }

}
