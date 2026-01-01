import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {

        // Game context, has two clicks
        OnClickListener rightListener = () -> System.out.println("Right Clicked");

        OnClickListener leftListener = new OnClickListener() {
            @Override
            public void onClick() {
                System.out.println("Left Clicked");
            }
        };

        rightListener.onClick();
        leftListener.onClick();

        Supplier<Integer> supplier = () -> 100;
        int result = new Main().testFunction(supplier);
        System.out.println(result);
    }

    public Integer testFunction(Supplier<Integer> supplier) {
        return supplier.get();
    }

}