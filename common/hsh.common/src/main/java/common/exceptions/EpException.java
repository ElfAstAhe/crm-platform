/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common.exceptions;

/**
 * Общая ошибка уровня End Point
 * @author elf
 */
public class EpException extends Exception{
    
    public EpException(String message) {
        super(message);
    }
    
    public EpException(String message, Throwable cause) {
        super(message, cause);
    }
    
    protected EpException(String message, Throwable cause,
            boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}