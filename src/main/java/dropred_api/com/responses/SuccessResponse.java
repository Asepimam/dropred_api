package dropred_api.com.responses;

public class SuccessResponse<T> extends BaseResponse<T> {
    public SuccessResponse(String message, T data) {
        super(message, data, 200);
    }
    
}
