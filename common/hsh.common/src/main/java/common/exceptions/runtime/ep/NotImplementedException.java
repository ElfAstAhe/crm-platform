package common.exceptions.runtime.ep;

import javax.ws.rs.ServerErrorException;
import javax.ws.rs.core.Response;

public class NotImplementedException extends ServerErrorException {
    private static final long serialVersionUID = 1L;

    public NotImplementedException() {
        super(Response.Status.NOT_IMPLEMENTED);
    }
    public NotImplementedException(String message) {
        super(message, Response.Status.NOT_IMPLEMENTED);
    }
    public NotImplementedException(Response response) {
        super(ExceptionHelper.validate(response, Response.Status.NOT_IMPLEMENTED));
    }
    public NotImplementedException(String message, Response response) {
        super(message, ExceptionHelper.validate(response, Response.Status.NOT_IMPLEMENTED));
    }
    public NotImplementedException(Throwable cause) {
        super(Response.Status.NOT_IMPLEMENTED, cause);
    }
    public NotImplementedException(String message, Throwable cause) {
        super(message, Response.Status.NOT_IMPLEMENTED, cause);
    }
    public NotImplementedException(Response response, Throwable cause) {
        super(ExceptionHelper.validate(response, Response.Status.NOT_IMPLEMENTED), cause);
    }
    public NotImplementedException(String message, Response response, Throwable cause) {
        super(message, ExceptionHelper.validate(response, Response.Status.NOT_IMPLEMENTED), cause);
    }
}
