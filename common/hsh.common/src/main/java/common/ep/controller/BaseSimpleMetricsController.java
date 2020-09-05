package common.ep.controller;

import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.exporter.common.TextFormat;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.StreamingOutput;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 * base class of metrics controller
 *
 * @author elf
 */
public abstract class BaseSimpleMetricsController {
    @GET
    @Path("application")
    public StreamingOutput getApplicationMetrics() {
        return this::saveApplicationMetrics;
    }

    @GET
    public StreamingOutput getMetrics() {
        return getApplicationMetrics();
    }

    protected void saveApplicationMetrics(OutputStream outputStream) throws IOException {
        try (Writer writer = new OutputStreamWriter(outputStream)) {
            TextFormat.write004(writer, CollectorRegistry.defaultRegistry.metricFamilySamples());
        }
    }
}
