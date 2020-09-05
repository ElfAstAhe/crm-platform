package common.ep.filter;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

/**
 * base class of end to end request id filter
 *
 * @author elf
 */
public abstract class BaseEndToEndRequestIdFilter extends HttpFilter {
    public static final String HEADER_NAME_REQUEST_ID = "End-To-End-Request-Id";

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // проверяем наличие сквозного идентификатора, добавляем если нет
        HeaderMapRequestWrapper requestWrapper = new HeaderMapRequestWrapper(request);
        String headerValue = requestWrapper.getHeader(HEADER_NAME_REQUEST_ID);
        if (StringUtils.isEmpty(headerValue)) {
            headerValue = UUID.randomUUID().toString();
            requestWrapper.addHeader(HEADER_NAME_REQUEST_ID, headerValue);
        }
        // Добавляем сквозной идентификатор в ответ
        response.addHeader(HEADER_NAME_REQUEST_ID, headerValue);

        // кидаем дальше по цепочке
        super.doFilter(requestWrapper, response, chain);

/*
        // Проверяем наличие сквозного идентификатора, добавляем если нет
        if (StringUtils.isEmpty(response.getHeader(HEADER_NAME_REQUEST_ID))) {
            response.addHeader(HEADER_NAME_REQUEST_ID, headerValue);
        }
*/
    }
}
