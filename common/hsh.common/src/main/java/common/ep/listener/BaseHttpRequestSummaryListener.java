package common.ep.listener;

import common.ep.metrics.Metrics;
import io.prometheus.client.Summary;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

/**
 * base class of summary bytes and latency metrics
 *
 * @author elf
 */
public class BaseHttpRequestSummaryListener implements ServletRequestListener {
    protected static final Summary requestReceivedBytes = Summary.build()
            .name(Metrics.Metrica.REQUEST_SUMMARY_SIZE_BYTES)
            .help(Metrics.Help.REQUEST_SUMMARY_SIZE_BYTES)
            .quantile(0.5, 0.01)
            .quantile(0.9, 0.01)
            .quantile(0.95, 0.01)
            .quantile(1.0, 0.01)
            .labelNames(Metrics.Label.METHOD, Metrics.Label.PATH_URI)
            .register();
    protected static final Summary requestLatencySummary = Summary.build()
            .name(Metrics.Metrica.REQUEST_SUMMARY_LATENCY_SECONDS)
            .help(Metrics.Help.REQUEST_SUMMARY_LATENCY_SECONDS)
            .quantile(0.5, 0.01)
            .quantile(0.9, 0.01)
            .quantile(0.95, 0.01)
            .quantile(1.0, 0.01)
            .labelNames(Metrics.Label.METHOD, Metrics.Label.PATH_URI)
            .register();
    protected Summary.Timer timer;

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        HttpServletRequest req = (HttpServletRequest) sre.getServletRequest();
        timer = null;
        if (req != null) {
            timer = requestLatencySummary.labels(req.getMethod(), req.getRequestURI()).startTimer();
        }
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        HttpServletRequest req = (HttpServletRequest) sre.getServletRequest();
        if (req != null && timer != null) {
            requestReceivedBytes.labels(req.getMethod(), req.getRequestURI()).observe(req.getContentLengthLong());
            timer.observeDuration();
        }
    }
}
