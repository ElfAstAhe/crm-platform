package client.audit;

import common.ep.client.BaseClient;
import common.ep.client.BaseCrudClient;
import dto.audit.DataAudit;

import javax.net.ssl.HostnameVerifier;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import java.util.List;
import java.util.concurrent.ExecutorService;

public class DataAuditClient extends BaseCrudClient<DataAudit> {
    private static final String defaultMimeType = "application/json";
    private static final String resourcePath = "dataAudit";

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
        super(baseUri,
                resourcePath,
                connectTimeoutMilliseconds,
                readTimeoutMilliseconds,
                mediaType,
                sslHostnameVerifier,
                executorService,
                DataAudit.class);
    }

    @Override
    protected Long getId(DataAudit instance) {
        return instance.getId();
    }

    @Override
    protected String getJwt() {
        return null;
    }

    @Override
    protected GenericType<List<DataAudit>> getListGenericType() {
        return new GenericType<List<DataAudit>>(){};
    }
}
