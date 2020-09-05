package common.exceptions.cryptography;

public class CryptoUtilException extends Exception {
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
