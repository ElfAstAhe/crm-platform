/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common.exceptions;

/**
 * Общая ошибка уровня BLL
 * @author elf
 */
public class BllException extends Exception{

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
