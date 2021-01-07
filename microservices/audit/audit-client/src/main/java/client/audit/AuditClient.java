package client.audit;

import common.ep.client.BaseRestClient;

import javax.net.ssl.HostnameVerifier;
import javax.ws.rs.client.WebTarget;
import java.util.concurrent.ExecutorService;

public class AuditClient extends BaseRestClient {
    private static final String PATH_URI = "api/v1";
    private static final String defaultMimeType = "application/json";
    private static final String resourcePath = "audit";

    private final WebTarget resourceTarget;

    public AuditClient(String baseUri) {
        this(baseUri, null);
    }

    public AuditClient(String baseUri, ExecutorService executorService) {
        this(baseUri,
                DEFAULT_CONNECT_TIMEOUT_MILLISECONDS,
                DEFAULT_READ_TIMEOUT_MILLISECONDS,
                defaultMimeType,
                null,
                executorService);
    }

    public AuditClient(String baseUri,
                               long connectTimeoutMilliseconds,
                               long readTimeoutMilliseconds,
                               String mediaType,
                               HostnameVerifier sslHostnameVerifier,
                               ExecutorService executorService) {
        super(baseUri,
                connectTimeoutMilliseconds,
                readTimeoutMilliseconds,
                mediaType,
                sslHostnameVerifier,
                executorService);
        resourceTarget = getBaseTarget().path(PATH_URI).path(resourcePath);
    }
}
