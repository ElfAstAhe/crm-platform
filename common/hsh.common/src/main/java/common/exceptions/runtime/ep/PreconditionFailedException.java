package common.exceptions.runtime.ep;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.Response;

public class PreconditionFailedException extends ClientErrorException {
    private static final long serialVersionUID = 1L;

    public PreconditionFailedException() {
        super(Response.Status.PRECONDITION_FAILED);
    }
    public PreconditionFailedException(String message) {
        super(message, Response.Status.PRECONDITION_FAILED);
    }
    public PreconditionFailedException(Response response) {
        super(ExceptionHelper.validate(response, Response.Status.PRECONDITION_FAILED));
    }
    public PreconditionFailedException(String message, Response response) {
        super(message, ExceptionHelper.validate(response, Response.Status.PRECONDITION_FAILED));
    }
    public PreconditionFailedException(Throwable cause) {
        super(Response.Status.PRECONDITION_FAILED, cause);
    }
    public PreconditionFailedException(String message, Throwable cause) {
        super(message, Response.Status.PRECONDITION_FAILED, cause);
    }
    public PreconditionFailedException(Response response, Throwable cause) {
        super(ExceptionHelper.validate(response, Response.Status.PRECONDITION_FAILED), cause);
    }
    public PreconditionFailedException(String message, Response response, Throwable cause) {
        super(message, ExceptionHelper.validate(response, Response.Status.PRECONDITION_FAILED), cause);
    }
}
