package common.dto.helper;

import common.dto.ExceptionDto;
import common.web.MimeTypes;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 *
 * @author elf
 */
public class ExceptionDtoHelper {
    private static final String HEADER_ACCEPT = "Accept";

    /**
     * получить корректный content-type
     * @param request http servlet request
     * @return correct content-type
     */
    public static String getCorrectContentType(HttpServletRequest request) {
        String accept = request.getHeader(HEADER_ACCEPT);
        return StringUtils.containsAny(
                accept,
                MimeTypes.Application.JSON,
                MimeTypes.Application.XML,
                MimeTypes.Application.YAML) ? accept : MimeTypes.Application.JSON;
    }

    /**
     * Конвертировать в dto
     * @param ex exception
     * @return dto
     */
    public static ExceptionDto toDto(Throwable ex) {
        if (ex == null)
            return null;
        
        return new ExceptionDto(
                ex.getClass().getName(),
                ex.getMessage(),
                getStackTrace(ex));
    }

    /**
     * Получить stackTrace в виде строки
     * @param ex exception
     * @return stack trace
     */
    private static String getStackTrace(Throwable ex) {
        StringWriter sw = new StringWriter();
        ex.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }
}
