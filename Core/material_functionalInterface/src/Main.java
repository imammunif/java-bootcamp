import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {

        // Game context, has two clicks
        OnClickListener rightListener = new OnClickListener() {
            @Override
            public void onClick() {
                System.out.println("Right Clicked");
            }
        };
        rightListener.onClick();

        OnClickListener leftListener = new OnClickListener() {
            @Override
            public void onClick() {
                System.out.println("Left Clicked");
            }
        };
        leftListener.onClick();

        Supplier<Integer> supplier = new Supplier<Integer>() {
            @Override
            public Integer get() {
                return 100;
            }
        };
        int result = supplier.get();

        new Main().testFunction(supplier);
    }

    public Integer testFunction(Supplier<Integer> supplier) {
        return supplier.get();
    }

}