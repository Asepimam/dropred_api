package dropred_api.com.exceptions;



import dropred_api.com.responses.ErrorResponse;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class GlobalExceptionHandler implements ExceptionMapper<ExceptionBase> {

   @Override
   public Response toResponse(ExceptionBase arg) {
       ErrorResponse<String> errorResponse = new ErrorResponse<>(arg.getStatusCode(), arg.getMessage());
       return Response.status(arg.getStatusCode()).entity(errorResponse).build();
   }
   
}
