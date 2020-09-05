package common.exceptions.ep;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.Response;

public class PaymentRequiredException extends ClientErrorException {
    private static final long serialVersionUID = 1L;

    public PaymentRequiredException() {
        super(Response.Status.PAYMENT_REQUIRED);
    }
    public PaymentRequiredException(String message) {
        super(message, Response.Status.PAYMENT_REQUIRED);
    }
    public PaymentRequiredException(Response response) {
        super(ExceptionHelper.validate(response, Response.Status.PAYMENT_REQUIRED));
    }
    public PaymentRequiredException(String message, Response response) {
        super(message, ExceptionHelper.validate(response, Response.Status.PAYMENT_REQUIRED));
    }
    public PaymentRequiredException(Throwable cause) {
        super(Response.Status.PAYMENT_REQUIRED, cause);
    }
    public PaymentRequiredException(String message, Throwable cause) {
        super(message, Response.Status.PAYMENT_REQUIRED, cause);
    }
    public PaymentRequiredException(Response response, Throwable cause) {
        super(ExceptionHelper.validate(response, Response.Status.PAYMENT_REQUIRED), cause);
    }
    public PaymentRequiredException(String message, Response response, Throwable cause) {
        super(message, ExceptionHelper.validate(response, Response.Status.PAYMENT_REQUIRED), cause);
    }
}
