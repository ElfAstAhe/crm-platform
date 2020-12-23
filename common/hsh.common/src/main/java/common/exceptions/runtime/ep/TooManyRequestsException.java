package common.exceptions.runtime.ep;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.Response;

public class TooManyRequestsException extends ClientErrorException {
    private static final long serialVersionUID = 1L;

    public TooManyRequestsException() {
        super(Response.Status.TOO_MANY_REQUESTS);
    }
    public TooManyRequestsException(String message) {
        super(message, Response.Status.TOO_MANY_REQUESTS);
    }
    public TooManyRequestsException(Response response) {
        super(ExceptionHelper.validate(response, Response.Status.TOO_MANY_REQUESTS));
    }
    public TooManyRequestsException(String message, Response response) {
        super(message, ExceptionHelper.validate(response, Response.Status.TOO_MANY_REQUESTS));
    }
    public TooManyRequestsException(Throwable cause) {
        super(Response.Status.TOO_MANY_REQUESTS, cause);
    }
    public TooManyRequestsException(String message, Throwable cause) {
        super(message, Response.Status.TOO_MANY_REQUESTS, cause);
    }
    public TooManyRequestsException(Response response, Throwable cause) {
        super(ExceptionHelper.validate(response, Response.Status.TOO_MANY_REQUESTS), cause);
    }
    public TooManyRequestsException(String message, Response response, Throwable cause) {
        super(message, ExceptionHelper.validate(response, Response.Status.TOO_MANY_REQUESTS), cause);
    }
}
