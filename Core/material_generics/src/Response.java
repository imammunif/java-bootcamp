public class Response {
    private Object data; // DATA CAN BE VARIOUS TYPE
    private String message;

    public Object getData() {
        return data;
    }
    public String getMessage() {
        return message;
    }
    public void setData(Object data) {
        this.data = data;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
