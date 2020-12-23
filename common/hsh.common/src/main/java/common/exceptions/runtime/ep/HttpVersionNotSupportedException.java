package common.exceptions.runtime.ep;

import javax.ws.rs.ServerErrorException;
import javax.ws.rs.core.Response;

public class HttpVersionNotSupportedException extends ServerErrorException {
    private static final long serialVersionUID = 1L;

    public HttpVersionNotSupportedException() {
        super(Response.Status.HTTP_VERSION_NOT_SUPPORTED);
    }
    public HttpVersionNotSupportedException(String message) {
        super(message, Response.Status.HTTP_VERSION_NOT_SUPPORTED);
    }
    public HttpVersionNotSupportedException(Response response) {
        super(ExceptionHelper.validate(response, Response.Status.HTTP_VERSION_NOT_SUPPORTED));
    }
    public HttpVersionNotSupportedException(String message, Response response) {
        super(message, ExceptionHelper.validate(response, Response.Status.HTTP_VERSION_NOT_SUPPORTED));
    }
    public HttpVersionNotSupportedException(Throwable cause) {
        super(Response.Status.HTTP_VERSION_NOT_SUPPORTED, cause);
    }
    public HttpVersionNotSupportedException(String message, Throwable cause) {
        super(message, Response.Status.HTTP_VERSION_NOT_SUPPORTED, cause);
    }
    public HttpVersionNotSupportedException(Response response, Throwable cause) {
        super(ExceptionHelper.validate(response, Response.Status.HTTP_VERSION_NOT_SUPPORTED), cause);
    }
    public HttpVersionNotSupportedException(String message, Response response, Throwable cause) {
        super(message, ExceptionHelper.validate(response, Response.Status.HTTP_VERSION_NOT_SUPPORTED), cause);
    }
}
