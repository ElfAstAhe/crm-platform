package org.hsh.crm.ms.audit.client;

import org.hsh.crm.ms.audit.dto.DataAudit;
import org.hsh.ms.common.ep.EpCommon;
import org.hsh.ms.common.ep.client.BaseCrudClient;
import org.hsh.ms.common.web.MimeTypes;

import javax.net.ssl.HostnameVerifier;
import javax.ws.rs.core.GenericType;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.function.Supplier;

public class DataAuditClient extends BaseCrudClient<DataAudit> {
    public static final String RESOURCE_PATH = "dataAudit";

    public DataAuditClient(String baseUri,
                           Supplier<String> jwtSupplier,
                           HostnameVerifier sslHostnameVerifier,
                           ExecutorService executorService) {
        this(baseUri,
             DEFAULT_CONNECT_TIMEOUT_MILLISECONDS,
             DEFAULT_READ_TIMEOUT_MILLISECONDS,
             jwtSupplier,
             sslHostnameVerifier,
             executorService);
    }
    public DataAuditClient(String baseUri,
                           long connectionTimeoutMillis,
                           long readTimeoutMillis,
                           Supplier<String> jwtSupplier,
                           HostnameVerifier sslHostnameVerifier,
                           ExecutorService executorService) {
        super(baseUri,
              EpCommon.RsApi.V1,
              RESOURCE_PATH,
              connectionTimeoutMillis,
              readTimeoutMillis,
              MimeTypes.Application.JSON,
              jwtSupplier,
              sslHostnameVerifier,
              executorService,
              DataAudit.class);
    }

    @Override
    protected Long getId(DataAudit dto) {
        return dto.getId();
    }

    @Override
    protected GenericType<List<DataAudit>> getListGenericType() {
        return new GenericType<List<DataAudit>>(){};
    }
}
