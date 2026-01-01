public class Main {
    public static void main(String[] args) {
        Response<String> response1 = new Response<>(); // this will error
        response1.setData("data");
        response1.setMessage("Success");

        Response<Boolean> response2 = new Response<>(); // this will error
        response2.setData(true);
        response2.setMessage("Success");

        Response<Person> response3 = new Response<>();
        response3.setData(new Person());
        response3.setMessage("Success");

    }
}