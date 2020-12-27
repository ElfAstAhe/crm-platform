package client.audit;

import common.ep.client.BaseClient;

import javax.net.ssl.HostnameVerifier;
import javax.ws.rs.client.WebTarget;
import java.util.concurrent.ExecutorService;

public class DataAuditClient extends BaseClient {
    private static final String defaultMimeType = "application/json";
    private static final String resourcePath = "dataAudit";
    private final WebTarget webTarget;

    public DataAuditClient(String baseUri) {
        this(baseUri, null);
    }

    public DataAuditClient(String baseUri, ExecutorService executorService) {
        this(baseUri,
                DEFAULT_CONNECT_TIMEOUT_MILLISECONDS,
                DEFAULT_READ_TIMEOUT_MILLISECONDS,
                defaultMimeType,
                null,
                executorService);
    }

    public DataAuditClient(String baseUri,
                           long connectTimeoutMilliseconds,
                           long readTimeoutMilliseconds,
                           String mediaType,
                           HostnameVerifier sslHostnameVerifier,
                           ExecutorService executorService) {
        super(connectTimeoutMilliseconds, readTimeoutMilliseconds, mediaType, sslHostnameVerifier, executorService);
        webTarget = getClient().target(baseUri).path(PATH_URI).path(resourcePath);
    }
}
