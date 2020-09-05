package common.exceptions.ep;

import javax.ws.rs.ServerErrorException;
import javax.ws.rs.core.Response;

public class BadGatewayException extends ServerErrorException {
    private static final long serialVersionUID = 1L;

    public BadGatewayException() {
        super(Response.Status.BAD_GATEWAY);
    }
    public BadGatewayException(String message) {
        super(message, Response.Status.BAD_GATEWAY);
    }
    public BadGatewayException(Response response) {
        super(ExceptionHelper.validate(response, Response.Status.BAD_GATEWAY));
    }
    public BadGatewayException(String message, Response response) {
        super(message, ExceptionHelper.validate(response, Response.Status.BAD_GATEWAY));
    }
    public BadGatewayException(Throwable cause) {
        super(Response.Status.BAD_GATEWAY, cause);
    }
    public BadGatewayException(String message, Throwable cause) {
        super(message, Response.Status.BAD_GATEWAY, cause);
    }
    public BadGatewayException(Response response, Throwable cause) {
        super(ExceptionHelper.validate(response, Response.Status.BAD_GATEWAY), cause);
    }
    public BadGatewayException(String message, Response response, Throwable cause) {
        super(message, ExceptionHelper.validate(response, Response.Status.BAD_GATEWAY), cause);
    }
}
