package common.exceptions.ep;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.Response;

public class ResourceGoneException extends ClientErrorException {
    private static final long serialVersionUID = 1L;

    public ResourceGoneException() {
        super(Response.Status.GONE);
    }
    public ResourceGoneException(String message) {
        super(message, Response.Status.GONE);
    }
    public ResourceGoneException(Response response) {
        super(ExceptionHelper.validate(response, Response.Status.GONE));
    }
    public ResourceGoneException(String message, Response response) {
        super(message, ExceptionHelper.validate(response, Response.Status.GONE));
    }
    public ResourceGoneException(Throwable cause) {
        super(Response.Status.GONE, cause);
    }
    public ResourceGoneException(String message, Throwable cause) {
        super(message, Response.Status.GONE, cause);
    }
    public ResourceGoneException(Response response, Throwable cause) {
        super(ExceptionHelper.validate(response, Response.Status.GONE), cause);
    }
    public ResourceGoneException(String message, Response response, Throwable cause) {
        super(message, ExceptionHelper.validate(response, Response.Status.GONE), cause);
    }
}
