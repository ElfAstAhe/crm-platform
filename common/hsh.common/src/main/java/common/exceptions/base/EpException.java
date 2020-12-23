package common.exceptions.base;

/**
 * Общая ошибка уровня End Point
 *
 * @author elf
 */
public class EpException extends Exception {
    private static final long serialVersionUID = 1L;

    public EpException(String message) {
        super(message);
    }

    public EpException(String message, Throwable cause) {
        super(message, cause);
    }

    protected EpException(String message, Throwable cause,
                          boolean enableSuppression,
                          boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
