package common.exceptions.ep;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.Response;

public class PreconditionRequiredException extends ClientErrorException {
    private static final long serialVersionUID = 1L;

    public PreconditionRequiredException() {
        super(Response.Status.PRECONDITION_REQUIRED);
    }
    public PreconditionRequiredException(String message) {
        super(message, Response.Status.PRECONDITION_REQUIRED);
    }
    public PreconditionRequiredException(Response response) {
        super(ExceptionHelper.validate(response, Response.Status.PRECONDITION_REQUIRED));
    }
    public PreconditionRequiredException(String message, Response response) {
        super(message, ExceptionHelper.validate(response, Response.Status.PRECONDITION_REQUIRED));
    }
    public PreconditionRequiredException(Throwable cause) {
        super(Response.Status.PRECONDITION_REQUIRED, cause);
    }
    public PreconditionRequiredException(String message, Throwable cause) {
        super(message, Response.Status.PRECONDITION_REQUIRED, cause);
    }
    public PreconditionRequiredException(Response response, Throwable cause) {
        super(ExceptionHelper.validate(response, Response.Status.PRECONDITION_REQUIRED), cause);
    }
    public PreconditionRequiredException(String message, Response response, Throwable cause) {
        super(message, ExceptionHelper.validate(response, Response.Status.PRECONDITION_REQUIRED), cause);
    }
}
