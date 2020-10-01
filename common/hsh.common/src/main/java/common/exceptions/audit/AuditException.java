package common.exceptions.audit;

public class AuditException extends Exception{
    public AuditException(String message) {
        super(message);
    }

    public AuditException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuditException(String message,
                        String remoteException,
                        String remoteMessage,
                        String remoteStackTrace) {
        super(message);
    }

    protected AuditException(String message, Throwable cause,
                           boolean enableSuppression,
                           boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
