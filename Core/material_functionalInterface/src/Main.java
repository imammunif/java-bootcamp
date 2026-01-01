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
    }
}