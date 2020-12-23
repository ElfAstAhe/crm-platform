package common.exceptions.runtime.base;

/**
 * Общая RunTime ошибка уровня Dal
 *
 * @author elf
 */
public class DalRuntimeException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public DalRuntimeException(String message) {
        super(message);
    }

    public DalRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    protected DalRuntimeException(String message, Throwable cause,
                                  boolean enableSuppression,
                                  boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
