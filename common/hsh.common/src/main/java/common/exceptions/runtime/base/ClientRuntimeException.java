package common.exceptions.runtime.base;

import common.dto.ExceptionDto;

/**
 * runtime ошибка rest клиента
 *
 * @author elf
 */
public class ClientRuntimeException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private final int statusCode;
    private final String remoteException;
    private final String remoteMessage;
    private final String remoteStackTrace;
    private static final String TO_STRING_FORMAT = "%s, remote: exception [%s] message [%s] stack trace [%s]";

    public ClientRuntimeException(String message,
                                  int statusCode,
                                  String remoteException,
                                  String remoteMessage,
                                  String remoteStackTrace) {
        super(message);
        this.statusCode = statusCode;
        this.remoteException = remoteException;
        this.remoteMessage = remoteMessage;
        this.remoteStackTrace = remoteStackTrace;
    }

    public ClientRuntimeException(String message,
                                  int statusCode,
                                  ExceptionDto remoteException) {
        super(message);
        this.statusCode = statusCode;
        this.remoteException = remoteException.getException();
        this.remoteMessage = remoteException.getMessage();
        this.remoteStackTrace = remoteException.getStackTrace();
    }

    @Override
    public String toString() {
        return String.format(TO_STRING_FORMAT, this.getMessage(), this.remoteException, this.remoteMessage, this.remoteStackTrace);
    }
}
