package common.exceptions.runtime.ep;

import javax.ws.rs.ServerErrorException;
import javax.ws.rs.core.Response;

public class GatewayTimeoutException extends ServerErrorException {
    private static final long serialVersionUID = 1L;

    public GatewayTimeoutException() {
        super(Response.Status.GATEWAY_TIMEOUT);
    }
    public GatewayTimeoutException(String message) {
        super(message, Response.Status.GATEWAY_TIMEOUT);
    }
    public GatewayTimeoutException(Response response) {
        super(ExceptionHelper.validate(response, Response.Status.GATEWAY_TIMEOUT));
    }
    public GatewayTimeoutException(String message, Response response) {
        super(message, ExceptionHelper.validate(response, Response.Status.GATEWAY_TIMEOUT));
    }
    public GatewayTimeoutException(Throwable cause) {
        super(Response.Status.GATEWAY_TIMEOUT, cause);
    }
    public GatewayTimeoutException(String message, Throwable cause) {
        super(message, Response.Status.GATEWAY_TIMEOUT, cause);
    }
    public GatewayTimeoutException(Response response, Throwable cause) {
        super(ExceptionHelper.validate(response, Response.Status.GATEWAY_TIMEOUT), cause);
    }
    public GatewayTimeoutException(String message, Response response, Throwable cause) {
        super(message, ExceptionHelper.validate(response, Response.Status.GATEWAY_TIMEOUT), cause);
    }
}
