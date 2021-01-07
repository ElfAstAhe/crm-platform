package common.exceptions.runtime.base;

/**
 * runtime ошибка rest клиента
 *
 * @author elf
 */
public class ClientRuntimeException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private final int statusCode;
    private final String responseBody;
    private static final String TO_STRING_FORMAT = "%s, remote: status[%s] body[%s]";

    public ClientRuntimeException(String message,
                                  int statusCode,
                                  String responseBody) {
        super(message);
        this.statusCode = statusCode;
        this.responseBody = responseBody;
    }

    @Override
    public String toString() {
        return String.format(TO_STRING_FORMAT, this.getMessage(), this.statusCode, this.responseBody);
    }

    public String getResponseBody() {
        return responseBody;
    }
}
