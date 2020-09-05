package common.ep.listener;

import common.ep.metrics.Metrics;
import io.prometheus.client.Histogram;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

public class BaseHttpRequestHistogramListener implements ServletRequestListener {
    protected static final Histogram requestHistogram = Histogram.build()
            .name(Metrics.Metrica.REQUEST_HISTOGRAM_LATENCY_SECONDS)
            .help(Metrics.Help.REQUEST_HISTOGRAM_LATENCY_SECONDS)
            .buckets(0.5, 0.9, 0.95, 1.0)
            .labelNames(Metrics.Label.METHOD, Metrics.Label.PATH_URI)
            .register();
    protected Histogram.Timer timer;

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        HttpServletRequest req = (HttpServletRequest) sre.getServletRequest();
        timer = null;
        if (req != null) {
            timer = requestHistogram.labels(req.getMethod(), req.getRequestURI()).startTimer();
        }
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        HttpServletRequest req = (HttpServletRequest) sre.getServletRequest();
        if (req != null && timer != null) {
            timer.observeDuration();
        }
    }
}
