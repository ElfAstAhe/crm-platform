package org.hsh.crm.ms.common.bll.services.audit;

import javax.net.ssl.HostnameVerifier;
import java.util.function.Supplier;

public class AuditClientParams {
    private final String baseUri;
    private final long connectionTimeoutMillis;
    private final long readTimeoutMillis;
    private final Supplier<String> jwtSupplier;
    private final HostnameVerifier sslHostnameVerifier;

    public AuditClientParams(String baseUri,
                             long connectionTimeoutMillis,
                             long readTimeoutMillis,
                             Supplier<String> jwtSupplier,
                             HostnameVerifier sslHostnameVerifier) {
        this.baseUri = baseUri;
        this.connectionTimeoutMillis = connectionTimeoutMillis;
        this.readTimeoutMillis = readTimeoutMillis;
        this.jwtSupplier = jwtSupplier;
        this.sslHostnameVerifier = sslHostnameVerifier;
    }

    public String getBaseUri() {
        return baseUri;
    }

    public long getConnectionTimeoutMillis() {
        return connectionTimeoutMillis;
    }

    public long getReadTimeoutMillis() {
        return readTimeoutMillis;
    }

    public Supplier<String> getJwtSupplier() {
        return jwtSupplier;
    }

    public HostnameVerifier getSslHostnameVerifier() {
        return sslHostnameVerifier;
    }
}
