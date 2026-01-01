import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {

        // Game context, has two clicks
        OnClickListener rightListener = (param1, param2) -> System.out.println("Right Clicked");

        OnClickListener leftListener = new OnClickListener() {
            @Override
            public void onClick(int param1, String param2) {
                System.out.println("Left Clicked");
            }
        };

        rightListener.onClick(100, "data1");
        leftListener.onClick(200, "data2");

        Supplier<Integer> supplier = () -> 100;
        int result = new Main().testFunction(supplier);
        System.out.println(result);
    }

    public Integer testFunction(Supplier<Integer> supplier) {
        return supplier.get();
    }

}