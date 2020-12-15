package common.exceptions;

/**
 * runtime ошибка миграции
 *
 * @author elf
 */
public class MigrationRuntimeException extends DalRuntimeException {
    public MigrationRuntimeException(String message) {
        super(message);
    }

    public MigrationRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    protected MigrationRuntimeException(String message, Throwable cause,
                                        boolean enableSuppression,
                                        boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
