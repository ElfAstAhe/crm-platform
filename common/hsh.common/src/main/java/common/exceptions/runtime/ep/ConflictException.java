package common.exceptions.runtime.ep;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.Response;

public class ConflictException extends ClientErrorException {
    private static final long serialVersionUID = 1L;

    public ConflictException() {
        super(Response.Status.CONFLICT);
    }
    public ConflictException(String message) {
        super(message, Response.Status.CONFLICT);
    }
    public ConflictException(Response response) {
        super(ExceptionHelper.validate(response, Response.Status.CONFLICT));
    }
    public ConflictException(String message, Response response) {
        super(message, ExceptionHelper.validate(response, Response.Status.CONFLICT));
    }
    public ConflictException(Throwable cause) {
        super(Response.Status.CONFLICT, cause);
    }
    public ConflictException(String message, Throwable cause) {
        super(message, Response.Status.CONFLICT, cause);
    }
    public ConflictException(Response response, Throwable cause) {
        super(ExceptionHelper.validate(response, Response.Status.CONFLICT), cause);
    }
    public ConflictException(String message, Response response, Throwable cause) {
        super(message, ExceptionHelper.validate(response, Response.Status.CONFLICT), cause);
    }
}
