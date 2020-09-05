package common.exceptions.ep;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.Response;

public class RequestTimeoutException extends ClientErrorException {
    private static final long serialVersionUID = 1L;

    public RequestTimeoutException() {
        super(Response.Status.REQUEST_TIMEOUT);
    }
    public RequestTimeoutException(String message) {
        super(message, Response.Status.REQUEST_TIMEOUT);
    }
    public RequestTimeoutException(Response response) {
        super(ExceptionHelper.validate(response, Response.Status.REQUEST_TIMEOUT));
    }
    public RequestTimeoutException(String message, Response response) {
        super(message, ExceptionHelper.validate(response, Response.Status.REQUEST_TIMEOUT));
    }
    public RequestTimeoutException(Throwable cause) {
        super(Response.Status.REQUEST_TIMEOUT, cause);
    }
    public RequestTimeoutException(String message, Throwable cause) {
        super(message, Response.Status.REQUEST_TIMEOUT, cause);
    }
    public RequestTimeoutException(Response response, Throwable cause) {
        super(ExceptionHelper.validate(response, Response.Status.REQUEST_TIMEOUT), cause);
    }
    public RequestTimeoutException(String message, Response response, Throwable cause) {
        super(message, ExceptionHelper.validate(response, Response.Status.REQUEST_TIMEOUT), cause);
    }
}
