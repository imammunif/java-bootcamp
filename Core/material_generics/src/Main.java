import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Object> list = new ArrayList<>();
        // the same
        List list1 = new ArrayList<>();

        list.add("data");
        list.add(true);
        list1.add("data");
        list1.add(true);
        String data1 = (String) list.get(1); // not error, by Casting the Parent
        String data2 = (String) list1.get(1); // not error, by Casting the Parent
    }
}