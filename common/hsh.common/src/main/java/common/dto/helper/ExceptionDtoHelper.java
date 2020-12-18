package common.dto.helper;

import common.dto.ExceptionDto;
import common.helpers.builder.ExceptionDtoBuilder;
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
    private static final String[] ACCEPTABLE_CONTENT_TYPES = new String[] {MimeTypes.Application.JSON,
            MimeTypes.Application.XML,
            MimeTypes.Application.YAML};
    private static final String DEFAULT_CONTENT_TYPE = MimeTypes.Application.JSON;

    /**
     * получить корректный content-type
     * @param request http servlet request
     * @return correct content-type
     */
    public static String getCorrectContentType(HttpServletRequest request) {
        String accept = request.getHeader(HEADER_ACCEPT);
        return StringUtils.containsAny(
                accept,
                ACCEPTABLE_CONTENT_TYPES) ? accept : DEFAULT_CONTENT_TYPE;
    }

    /**
     * Конвертировать в dto
     * @param ex exception
     * @return dto
     */
    public static ExceptionDto toDto(Throwable ex) {
        if (ex == null)
            return null;

        return ExceptionDtoBuilder.get()
                .setException(ex.getClass().getName())
                .setMessage(ex.getMessage())
                .setStackTrace(getStackTrace(ex))
                .build();
    }

    /**
     * Получить stackTrace в виде строки
     * @param ex exception
     * @return stack trace
     */
    private static String getStackTrace(Throwable ex) {
        if (ex == null)
            return null;

        StringWriter sw = new StringWriter();
        ex.printStackTrace(new PrintWriter(sw));

        return sw.toString();
    }
}
