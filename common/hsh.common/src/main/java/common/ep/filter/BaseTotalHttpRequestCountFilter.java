package common.ep.filter;

import common.ep.metrics.Metrics;
import io.prometheus.client.Counter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * base class of total request counter filter
 *
 * @author elf
 */
public abstract class BaseTotalHttpRequestCountFilter extends HttpFilter {
    protected static final Counter httpRequestCounter = Counter.build()
            .name(Metrics.Metrica.TOTAL_HTTP_REQUEST)
            .help(Metrics.Help.TOTAL_HTTP_REQUEST)
            .labelNames(Metrics.Label.STATUS, Metrics.Label.METHOD, Metrics.Label.PATH_URI)
            .register();

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // Кидаем дальше по цепочке
        super.doFilter(request, response, chain);

        // Работаем по ответу
        httpRequestCounter.labels(String.valueOf(response.getStatus()), request.getMethod(), request.getRequestURI()).inc();
    }
}
