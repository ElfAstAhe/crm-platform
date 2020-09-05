package common.exceptions.ep;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.Response;

public class ExpectationFailedException extends ClientErrorException {
    private static final long serialVersionUID = 1L;

    public ExpectationFailedException() {
        super(Response.Status.EXPECTATION_FAILED);
    }
    public ExpectationFailedException(String message) {
        super(message, Response.Status.EXPECTATION_FAILED);
    }
    public ExpectationFailedException(Response response) {
        super(ExceptionHelper.validate(response, Response.Status.EXPECTATION_FAILED));
    }
    public ExpectationFailedException(String message, Response response) {
        super(message, ExceptionHelper.validate(response, Response.Status.EXPECTATION_FAILED));
    }
    public ExpectationFailedException(Throwable cause) {
        super(Response.Status.EXPECTATION_FAILED, cause);
    }
    public ExpectationFailedException(String message, Throwable cause) {
        super(message, Response.Status.EXPECTATION_FAILED, cause);
    }
    public ExpectationFailedException(Response response, Throwable cause) {
        super(ExceptionHelper.validate(response, Response.Status.EXPECTATION_FAILED), cause);
    }
    public ExpectationFailedException(String message, Response response, Throwable cause) {
        super(message, ExceptionHelper.validate(response, Response.Status.EXPECTATION_FAILED), cause);
    }
}
