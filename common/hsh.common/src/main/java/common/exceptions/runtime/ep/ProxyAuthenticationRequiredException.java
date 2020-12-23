package common.exceptions.runtime.ep;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.Response;

public class ProxyAuthenticationRequiredException extends ClientErrorException {
    private static final long serialVersionUID = 1L;

    public ProxyAuthenticationRequiredException() {
        super(Response.Status.PROXY_AUTHENTICATION_REQUIRED);
    }
    public ProxyAuthenticationRequiredException(String message) {
        super(message, Response.Status.PROXY_AUTHENTICATION_REQUIRED);
    }
    public ProxyAuthenticationRequiredException(Response response) {
        super(ExceptionHelper.validate(response, Response.Status.PROXY_AUTHENTICATION_REQUIRED));
    }
    public ProxyAuthenticationRequiredException(String message, Response response) {
        super(message, ExceptionHelper.validate(response, Response.Status.PROXY_AUTHENTICATION_REQUIRED));
    }
    public ProxyAuthenticationRequiredException(Throwable cause) {
        super(Response.Status.PROXY_AUTHENTICATION_REQUIRED, cause);
    }
    public ProxyAuthenticationRequiredException(String message, Throwable cause) {
        super(message, Response.Status.PROXY_AUTHENTICATION_REQUIRED, cause);
    }
    public ProxyAuthenticationRequiredException(Response response, Throwable cause) {
        super(ExceptionHelper.validate(response, Response.Status.PROXY_AUTHENTICATION_REQUIRED), cause);
    }
    public ProxyAuthenticationRequiredException(String message, Response response, Throwable cause) {
        super(message, ExceptionHelper.validate(response, Response.Status.PROXY_AUTHENTICATION_REQUIRED), cause);
    }
}
