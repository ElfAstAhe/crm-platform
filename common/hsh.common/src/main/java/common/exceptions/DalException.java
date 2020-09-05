/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common.exceptions;

/**
 * Общая ошибка уровня DAL
 * @author elf
 */
public class DalException extends Exception{
    
    public DalException(String message) {
        super(message);
    }
    
    public DalException(String message, Throwable cause) {
        super(message, cause);
    }
    
    protected DalException(String message, Throwable cause,
            boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
