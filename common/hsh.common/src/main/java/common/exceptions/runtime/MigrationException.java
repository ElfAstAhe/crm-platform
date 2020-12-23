package common.exceptions.runtime;

import common.exceptions.runtime.base.DalRuntimeException;

/**
 * runtime ошибка миграции
 *
 * @author elf
 */
public class MigrationException extends DalRuntimeException {
    private static final long serialVersionUID = 1L;

    public MigrationException(String message) {
        super(message);
    }

    public MigrationException(String message, Throwable cause) {
        super(message, cause);
    }

    protected MigrationException(String message, Throwable cause,
                                 boolean enableSuppression,
                                 boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
