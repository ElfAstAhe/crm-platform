package common.dto;

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbPropertyOrder;
import javax.json.bind.config.PropertyOrderStrategy;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 *
 * @author elf
 */
@XmlRootElement(name = "exceptionDto")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonbPropertyOrder(value = PropertyOrderStrategy.ANY)
public final class ExceptionDto implements Serializable{
    private static final long serialVersionUID = 1L;
 
    @XmlElement(name = "exception")
    @JsonbProperty(value = "exception")
    private String exception;
    
    @XmlElement(name = "message")
    @JsonbProperty(value = "message")
    private String message;
    
    @XmlElement(name = "stackTrace")
    @JsonbProperty(value = "stackTrace")
    private String stackTrace;

    @XmlElement(name = "cause")
    @JsonbProperty(value = "cause")
    private ExceptionDto cause;

    // КОНСТРУКТОРЫ и ДЕСТРУКТОРЫ
    // По умолчанию
    public ExceptionDto(){
        // default constructor
    }
    
    public String getException() {
        return exception;
    }

    public String getMessage() {
        return message;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }

    public ExceptionDto getCause() {
        return cause;
    }

    public void setCause(ExceptionDto cause) {
        this.cause = cause;
    }
}
