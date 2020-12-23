package common.exceptions.runtime.ep;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.Response;

public class RequestedRangeNotSatisfiableException extends ClientErrorException {
    private static final long serialVersionUID = 1L;

    public RequestedRangeNotSatisfiableException() {
        super(Response.Status.REQUESTED_RANGE_NOT_SATISFIABLE);
    }
    public RequestedRangeNotSatisfiableException(String message) {
        super(message, Response.Status.REQUESTED_RANGE_NOT_SATISFIABLE);
    }
    public RequestedRangeNotSatisfiableException(Response response) {
        super(ExceptionHelper.validate(response, Response.Status.REQUESTED_RANGE_NOT_SATISFIABLE));
    }
    public RequestedRangeNotSatisfiableException(String message, Response response) {
        super(message, ExceptionHelper.validate(response, Response.Status.REQUESTED_RANGE_NOT_SATISFIABLE));
    }
    public RequestedRangeNotSatisfiableException(Throwable cause) {
        super(Response.Status.REQUESTED_RANGE_NOT_SATISFIABLE, cause);
    }
    public RequestedRangeNotSatisfiableException(String message, Throwable cause) {
        super(message, Response.Status.REQUESTED_RANGE_NOT_SATISFIABLE, cause);
    }
    public RequestedRangeNotSatisfiableException(Response response, Throwable cause) {
        super(ExceptionHelper.validate(response, Response.Status.REQUESTED_RANGE_NOT_SATISFIABLE), cause);
    }
    public RequestedRangeNotSatisfiableException(String message, Response response, Throwable cause) {
        super(message, ExceptionHelper.validate(response, Response.Status.REQUESTED_RANGE_NOT_SATISFIABLE), cause);
    }
}
