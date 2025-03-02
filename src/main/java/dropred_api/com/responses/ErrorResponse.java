package dropred_api.com.responses;

public class ErrorResponse<T> extends BaseResponse<T> {
    public ErrorResponse(int status,String message) {
        super(message, null, status);
    }
}