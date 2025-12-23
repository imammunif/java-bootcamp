public class Person {

    public int age = 25;

    public Person() {
        System.out.println("Constructor");
    }

    public Person(String param) {
        System.out.println("Constructor");
    }

    public Person(int param) {
        System.out.println("Constructor");
    }

    public void test() {
        System.out.println("Test");
    }

    public static void main(String[] args) {
        System.out.println("Hallo ini main");
    }

}
