package dropred_api.com.exceptions;

public class ExceptionBase extends RuntimeException {
    private final int statusCode;
    private final String message;

    public ExceptionBase(int statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    
}