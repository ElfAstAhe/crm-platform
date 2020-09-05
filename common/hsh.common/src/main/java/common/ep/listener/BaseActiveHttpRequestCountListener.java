package common.ep.listener;

import common.ep.metrics.Metrics;
import io.prometheus.client.Gauge;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

/**
 * base class of active http request gauge listener
 *
 * @author elf
 */
public abstract class BaseActiveHttpRequestCountListener implements ServletRequestListener {
    protected static final Gauge activeRequestCount = Gauge.build()
            .name(Metrics.Metrica.ACTIVE_HTTP_REQUEST)
            .help(Metrics.Help.ACTIVE_HTTP_REQUEST)
            .labelNames(Metrics.Label.METHOD, Metrics.Label.PATH_URI)
            .register();

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        HttpServletRequest req = (HttpServletRequest) sre.getServletRequest();
        if (req != null) {
            activeRequestCount.labels(req.getMethod(), req.getRequestURI()).inc();
        }
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        HttpServletRequest req = (HttpServletRequest) sre.getServletRequest();
        if (req != null) {
            activeRequestCount.labels(req.getMethod(), req.getRequestURI()).dec();
        }
    }
}
