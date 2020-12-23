package common.exceptions;

public class CryptoUtilException extends Exception {
    private static final long serialVersionUID = 1L;

    public CryptoUtilException(String message) {
        super(message);
    }

    public CryptoUtilException(String message, Throwable cause) {
        super(message, cause);
    }

    protected CryptoUtilException(String message, Throwable cause,
                                  boolean enableSuppression,
                                  boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
