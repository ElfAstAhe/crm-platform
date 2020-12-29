package common.exceptions.runtime.ep;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.Response;

public class RequestUriTooLongException extends ClientErrorException {
    private static final long serialVersionUID = 1L;

    public RequestUriTooLongException() {
        super(Response.Status.REQUEST_URI_TOO_LONG);
    }
    public RequestUriTooLongException(String message) {
        super(message, Response.Status.REQUEST_URI_TOO_LONG);
    }
    public RequestUriTooLongException(Response response) {
        super(ExceptionHelper.validate(response, Response.Status.REQUEST_URI_TOO_LONG));
    }
    public RequestUriTooLongException(String message, Response response) {
        super(message, ExceptionHelper.validate(response, Response.Status.REQUEST_URI_TOO_LONG));
    }
    public RequestUriTooLongException(Throwable cause) {
        super(Response.Status.REQUEST_URI_TOO_LONG, cause);
    }
    public RequestUriTooLongException(String message, Throwable cause) {
        super(message, Response.Status.REQUEST_URI_TOO_LONG, cause);
    }
    public RequestUriTooLongException(Response response, Throwable cause) {
        super(ExceptionHelper.validate(response, Response.Status.REQUEST_URI_TOO_LONG), cause);
    }
    public RequestUriTooLongException(String message, Response response, Throwable cause) {
        super(message, ExceptionHelper.validate(response, Response.Status.REQUEST_URI_TOO_LONG), cause);
    }
}
