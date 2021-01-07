package client.audit;

import common.ep.client.BaseCrudClient;
import dto.audit.DataAudit;
import dto.audit.SecurityAudit;

import javax.net.ssl.HostnameVerifier;
import javax.ws.rs.core.GenericType;
import java.util.List;
import java.util.concurrent.ExecutorService;

public class SecurityAuditClient extends BaseCrudClient<SecurityAudit> {
    private static final String defaultMimeType = "application/json";
    private static final String resourcePath = "securityAudit";

    public SecurityAuditClient(String baseUri) {
        this(baseUri, null);
    }

    public SecurityAuditClient(String baseUri, ExecutorService executorService) {
        this(baseUri,
                DEFAULT_CONNECT_TIMEOUT_MILLISECONDS,
                DEFAULT_READ_TIMEOUT_MILLISECONDS,
                defaultMimeType,
                null,
                executorService);
    }

    public SecurityAuditClient(String baseUri,
                           long connectTimeoutMilliseconds,
                           long readTimeoutMilliseconds,
                           String mediaType,
                           HostnameVerifier sslHostnameVerifier,
                           ExecutorService executorService) {
        super(baseUri,
                resourcePath,
                connectTimeoutMilliseconds,
                readTimeoutMilliseconds,
                mediaType,
                sslHostnameVerifier,
                executorService,
                SecurityAudit.class);
    }

    @Override
    protected Long getId(SecurityAudit instance) {
        return instance.getId();
    }

    @Override
    protected GenericType<List<SecurityAudit>> getListGenericType() {
        return new GenericType<List<SecurityAudit>>(){};
    }

    @Override
    protected String getJwt() {
        return null;
    }
}
