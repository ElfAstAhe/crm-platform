package common.exceptions.ep;

import javax.ws.rs.ServerErrorException;
import javax.ws.rs.core.Response;

public class NetworkAuthenticationRequiredException extends ServerErrorException {
    private static final long serialVersionUID = 1L;

    public NetworkAuthenticationRequiredException() {
        super(Response.Status.NETWORK_AUTHENTICATION_REQUIRED);
    }
    public NetworkAuthenticationRequiredException(String message) {
        super(message, Response.Status.NETWORK_AUTHENTICATION_REQUIRED);
    }
    public NetworkAuthenticationRequiredException(Response response) {
        super(ExceptionHelper.validate(response, Response.Status.NETWORK_AUTHENTICATION_REQUIRED));
    }
    public NetworkAuthenticationRequiredException(String message, Response response) {
        super(message, ExceptionHelper.validate(response, Response.Status.NETWORK_AUTHENTICATION_REQUIRED));
    }
    public NetworkAuthenticationRequiredException(Throwable cause) {
        super(Response.Status.NETWORK_AUTHENTICATION_REQUIRED, cause);
    }
    public NetworkAuthenticationRequiredException(String message, Throwable cause) {
        super(message, Response.Status.NETWORK_AUTHENTICATION_REQUIRED, cause);
    }
    public NetworkAuthenticationRequiredException(Response response, Throwable cause) {
        super(ExceptionHelper.validate(response, Response.Status.NETWORK_AUTHENTICATION_REQUIRED), cause);
    }
    public NetworkAuthenticationRequiredException(String message, Response response, Throwable cause) {
        super(message, ExceptionHelper.validate(response, Response.Status.NETWORK_AUTHENTICATION_REQUIRED), cause);
    }
}
