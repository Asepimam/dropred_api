package dropred_api.com.exceptions;

import jakarta.ws.rs.core.Response;

public class NotfoundException extends ExceptionBase {
    public NotfoundException(String message) {
        super(Response.Status.NOT_FOUND.getStatusCode(), message);
    }
    
}
