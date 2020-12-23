package common.exceptions.base;

/**
 * Общая ошибка уровня DAL
 *
 * @author elf
 */
public class DalException extends Exception {
    private static final long serialVersionUID = 1L;

    public DalException(String message) {
        super(message);
    }

    public DalException(String message, Throwable cause) {
        super(message, cause);
    }

    protected DalException(String message,
                           Throwable cause,
                           boolean enableSuppression,
                           boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
