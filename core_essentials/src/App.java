public class App {

    public static void main (String[] args) {
        //        App app1 = new App();
        //        App app2 = new App();
        Person person1 = new Person("data");
        person1.test();

        Person person2 = new Person();

        Person person3 = new Person(3);

        System.out.println(person1.age);
    }


}
