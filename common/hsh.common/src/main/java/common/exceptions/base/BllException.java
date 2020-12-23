package common.exceptions.base;

/**
 * Общая ошибка уровня BLL
 *
 * @author elf
 */
public class BllException extends Exception {
    private static final long serialVersionUID = 1L;

    public BllException(String message) {
        super(message);
    }

    public BllException(String message, Throwable cause) {
        super(message, cause);
    }

    public BllException(String message,
                        String remoteException,
                        String remoteMessage,
                        String remoteStackTrace) {
        super(message);
    }

    protected BllException(String message, Throwable cause,
                           boolean enableSuppression,
                           boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
