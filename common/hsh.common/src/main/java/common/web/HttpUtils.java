package common.web;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 *
 *
 * @author elf
 */
public class HttpUtils {
    /**
     * Преобразовывает данные {@code HttpServletRequest} в строку url без путей
     * <p>
     * Код взят со stack overflow
     * </p>
     * <a href=https://stackoverflow.com/questions/14249730/getting-url-to-a-webapp-context-the-base-url>stack overflow article</a>
     * <pre>
     * String uri = request.getRequestURI();
     * //uri = "/context/someAction"
     * String url = request.getRequestURL().toString();
     * // url = "http://server.name:8080/context/someAction"
     * String ctxPath = request.getContextPath();
     * // ctxPath = "/context";
     * url = url.replaceFirst(uri, "");
     * // url = "http://server.name:8080"
     * url = url + ctxPath;
     * //url = "http://server.name:8080/context"
     * </pre>
     * @param request запрос
     * @return строка: [схема]://[сервер]:[порт]
     */
    public static String getRequestUrlWithoutPath(HttpServletRequest request) {
        if (request == null)
            throw new IllegalArgumentException(StringUtils.EMPTY);

        String reqUri = request.getRequestURI();
        return request.getRequestURL()
                .toString()
                .replaceFirst(reqUri, "");
    }
}
