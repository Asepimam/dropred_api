package dropred_api.com.responses;

public class BaseResponse<T> {
    private String message;
    private T data;
    private int status;

    public BaseResponse(String message, T data, int status) {
        this.message = message;
        this.data = data;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public int getStatus() {
        return status;
    }
}
