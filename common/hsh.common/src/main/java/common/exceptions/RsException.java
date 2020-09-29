/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common.exceptions;

import common.dto.ExceptionDto;

/**
 * Ошибка сервиса
 * @author elf
 */
public class RsException extends Exception{
    
    private final int statusCode;
    private final String remoteException;
    private final String remoteMessage;
    private final String remoteStackTrace;
    private static final String TO_STRING_FORMAT = "%s, remote: exception [%s] message [%s] stack trace [%s]";
    
    public RsException(String message,
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
    
    public RsException(String message,
                       int statusCode,
                       ExceptionDto remoteException){
        super(message);
        this.statusCode = statusCode;
        this.remoteException = remoteException.getException();
        this.remoteMessage = remoteException.getMessage();
        this.remoteStackTrace = remoteException.getStackTrace();
    }

    public int getStatusCode() {
        return statusCode;
    }
    
    public String getRemoteException() {
        return remoteException;
    }

    public String getRemoteMessage() {
        return remoteMessage;
    }

    public String getRemoteStackTrace() {
        return remoteStackTrace;
    }
    
    @Override
    public String toString(){
        return String.format(TO_STRING_FORMAT, this.getMessage(), this.remoteException, this.remoteMessage, this.remoteStackTrace);
    }
}
