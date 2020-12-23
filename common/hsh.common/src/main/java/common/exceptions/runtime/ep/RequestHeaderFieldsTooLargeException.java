package common.exceptions.runtime.ep;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.Response;

public class RequestHeaderFieldsTooLargeException extends ClientErrorException {
    private static final long serialVersionUID = 1L;

    public RequestHeaderFieldsTooLargeException() {
        super(Response.Status.REQUEST_HEADER_FIELDS_TOO_LARGE);
    }
    public RequestHeaderFieldsTooLargeException(String message) {
        super(message, Response.Status.REQUEST_HEADER_FIELDS_TOO_LARGE);
    }
    public RequestHeaderFieldsTooLargeException(Response response) {
        super(ExceptionHelper.validate(response, Response.Status.REQUEST_HEADER_FIELDS_TOO_LARGE));
    }
    public RequestHeaderFieldsTooLargeException(String message, Response response) {
        super(message, ExceptionHelper.validate(response, Response.Status.REQUEST_HEADER_FIELDS_TOO_LARGE));
    }
    public RequestHeaderFieldsTooLargeException(Throwable cause) {
        super(Response.Status.REQUEST_HEADER_FIELDS_TOO_LARGE, cause);
    }
    public RequestHeaderFieldsTooLargeException(String message, Throwable cause) {
        super(message, Response.Status.REQUEST_HEADER_FIELDS_TOO_LARGE, cause);
    }
    public RequestHeaderFieldsTooLargeException(Response response, Throwable cause) {
        super(ExceptionHelper.validate(response, Response.Status.REQUEST_HEADER_FIELDS_TOO_LARGE), cause);
    }
    public RequestHeaderFieldsTooLargeException(String message, Response response, Throwable cause) {
        super(message, ExceptionHelper.validate(response, Response.Status.REQUEST_HEADER_FIELDS_TOO_LARGE), cause);
    }
}
