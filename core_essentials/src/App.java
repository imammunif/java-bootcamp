import com.dansmultipro.laundry.BasePerson;
import com.dansmultipro.laundry.Person;

public class App {

    public static void main (String[] args) {

        Person person1 = new Person();
        person1.test();

        BasePerson basePerson = new BasePerson(); //error
    }
}
