package common.exceptions.runtime.ep;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.Response;

public class RequestEntityTooLargeException extends ClientErrorException {
    private static final long serialVersionUID = 1L;

    public RequestEntityTooLargeException() {
        super(Response.Status.REQUEST_ENTITY_TOO_LARGE);
    }
    public RequestEntityTooLargeException(String message) {
        super(message, Response.Status.REQUEST_ENTITY_TOO_LARGE);
    }
    public RequestEntityTooLargeException(Response response) {
        super(ExceptionHelper.validate(response, Response.Status.REQUEST_ENTITY_TOO_LARGE));
    }
    public RequestEntityTooLargeException(String message, Response response) {
        super(message, ExceptionHelper.validate(response, Response.Status.REQUEST_ENTITY_TOO_LARGE));
    }
    public RequestEntityTooLargeException(Throwable cause) {
        super(Response.Status.REQUEST_ENTITY_TOO_LARGE, cause);
    }
    public RequestEntityTooLargeException(String message, Throwable cause) {
        super(message, Response.Status.REQUEST_ENTITY_TOO_LARGE, cause);
    }
    public RequestEntityTooLargeException(Response response, Throwable cause) {
        super(ExceptionHelper.validate(response, Response.Status.REQUEST_ENTITY_TOO_LARGE), cause);
    }
    public RequestEntityTooLargeException(String message, Response response, Throwable cause) {
        super(message, ExceptionHelper.validate(response, Response.Status.REQUEST_ENTITY_TOO_LARGE), cause);
    }
}
