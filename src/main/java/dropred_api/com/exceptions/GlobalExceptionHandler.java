package dropred_api.com.exceptions;

import dropred_api.com.responses.ErrorResponse;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.jboss.logging.Logger;

@Provider
public class GlobalExceptionHandler implements ExceptionMapper<Throwable> {

    private static final Logger LOGGER = Logger.getLogger(GlobalExceptionHandler.class);

    @Override
    public Response toResponse(Throwable exception) {
        LOGGER.error("Unhandled Exception: ", exception);
        

        if (exception instanceof ExceptionBase) {
            ExceptionBase customException = (ExceptionBase) exception;
            return Response.status(customException.getStatusCode())
                .entity(new ErrorResponse<>(customException.getStatusCode(), customException.getMessage()))
                .build();
        }

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
            .entity(new ErrorResponse<>(500, "An unexpected error occurred."))
            .build();
    }
}
