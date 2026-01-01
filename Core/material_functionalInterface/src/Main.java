public class Main {
    public static void main(String[] args) {
        OnClickListener listener = new OnClickListener() {
            @Override
            public void onClick() {
                System.out.println("Clicked");
            }
        };
        listener.onClick(); // call the instance
    }
}