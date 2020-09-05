package common.exceptions.ep;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.Response;

public class LengthRequiredException extends ClientErrorException {
    private static final long serialVersionUID = 1L;

    public LengthRequiredException() {
        super(Response.Status.LENGTH_REQUIRED);
    }
    public LengthRequiredException(String message) {
        super(message, Response.Status.LENGTH_REQUIRED);
    }
    public LengthRequiredException(Response response) {
        super(ExceptionHelper.validate(response, Response.Status.LENGTH_REQUIRED));
    }
    public LengthRequiredException(String message, Response response) {
        super(message, ExceptionHelper.validate(response, Response.Status.LENGTH_REQUIRED));
    }
    public LengthRequiredException(Throwable cause) {
        super(Response.Status.LENGTH_REQUIRED, cause);
    }
    public LengthRequiredException(String message, Throwable cause) {
        super(message, Response.Status.LENGTH_REQUIRED, cause);
    }
    public LengthRequiredException(Response response, Throwable cause) {
        super(ExceptionHelper.validate(response, Response.Status.LENGTH_REQUIRED), cause);
    }
    public LengthRequiredException(String message, Response response, Throwable cause) {
        super(message, ExceptionHelper.validate(response, Response.Status.LENGTH_REQUIRED), cause);
    }
}
