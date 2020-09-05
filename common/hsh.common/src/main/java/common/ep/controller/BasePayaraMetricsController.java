package common.ep.controller;

import common.web.HttpUtils;
import common.web.MimeTypes;
import org.apache.commons.io.IOUtils;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * платформо зависимая реализация базового контроллера метрик
 *
 * @author elf
 */
public abstract class BasePayaraMetricsController extends BaseSimpleMetricsController {
    private static final long CONNECT_TIME_OUT = 2000L;
    private static final long READ_TIME_OUT = 2000L;

    private static final String PATH_METRICS = "metrics";
    private static final String PATH_SYSTEM = "base";
    private static final String PATH_VENDOR = "vendor";

    private static final String BASE_PATH = "http://localhost:8080";

    private static final Logger logger = Logger.getLogger(BasePayaraMetricsController.class.getName());

    private Client client;
    private WebTarget target;

    @PostConstruct
    public void postConstruct() {
        client = ClientBuilder.newBuilder()
                .connectTimeout(CONNECT_TIME_OUT, TimeUnit.MILLISECONDS)
                .readTimeout(READ_TIME_OUT, TimeUnit.MILLISECONDS)
                .hostnameVerifier((s, sslSession) -> false)
                .executorService(getExecutor())
                .build();

        if (getRequest() != null) {
            String reqUrl = HttpUtils.getRequestUrlWithoutPath(getRequest());
            logger.info(String.format("requestUrl [%s]", reqUrl));
        }

//        target = client.target(HttpUtils.getRequestUrlWithoutPath(getRequest())).path(PATH_METRICS);
        target = client.target(BASE_PATH).path(PATH_METRICS);
    }

    @PreDestroy
    public void preDestroy() {
        client.close();
    }

    @GET
    @Path("vendor")
    public StreamingOutput getVendorMetrics() {
        return this::saveVendorMetrics;
    }

    @GET
    @Path("system")
    public StreamingOutput getSystemMetrics() {
        return this::saveSystemMetrics;
    }

    @GET
    @Override
    public StreamingOutput getMetrics() {
        return output -> {
            saveSystemMetrics(output);
            saveVendorMetrics(output);
            saveApplicationMetrics(output);
        };
    }

    protected void saveVendorMetrics(OutputStream outputStream) throws IOException {
        IOUtils.copy(getLocalVendorMetrics(), outputStream);
    }

    protected void saveSystemMetrics(OutputStream outputStream) throws IOException {
        IOUtils.copy(getLocalSystemMetrics(), outputStream);
    }

    protected abstract HttpServletRequest getRequest();

    protected abstract ExecutorService getExecutor();

    private InputStream getLocalSystemMetrics() {
        try (Response response = target.path(PATH_SYSTEM)
                .request()
                .accept(MimeTypes.Text.PLAIN)
                .get()) {
            return new ByteArrayInputStream(response.readEntity(String.class).getBytes());
        }
    }

    private InputStream getLocalVendorMetrics() {
        try (Response response = target.path(PATH_VENDOR)
                .request()
                .accept(MimeTypes.Text.PLAIN)
                .get()) {
            return new ByteArrayInputStream(response.readEntity(String.class).getBytes());
        }
    }
}
