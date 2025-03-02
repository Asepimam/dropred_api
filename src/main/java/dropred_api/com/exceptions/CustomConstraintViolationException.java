package dropred_api.com.exceptions;

import jakarta.ws.rs.core.Response;

public class CustomConstraintViolationException extends ExceptionBase {
    public CustomConstraintViolationException(String message) {
        super(Response.Status.BAD_REQUEST.getStatusCode(), message);
    }
    
}