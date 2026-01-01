public class Response<T> {
    private T data; // DATA CAN BE VARIOUS TYPE
    private String message;

    public T getData() {
        return data;
    }
    public String getMessage() {
        return message;
    }
    public void setData(T data) {
        this.data = data;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
