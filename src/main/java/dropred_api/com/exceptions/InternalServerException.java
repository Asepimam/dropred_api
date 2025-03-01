package dropred_api.com.exceptions;

import jakarta.ws.rs.core.Response;

public class InternalServerException extends ExceptionBase {
    public InternalServerException(String message) {
        super(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), message);
    }
}