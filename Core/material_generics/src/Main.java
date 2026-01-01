public class Main {
    public static void main(String[] args) {
        Response<String> response1 = new Response<>();
        response1.setData("data");
        response1.setMessage("Success");

        Response<Boolean> response2 = new Response<>();
        response2.setData(true);
        response2.setMessage("Success");

    }
}