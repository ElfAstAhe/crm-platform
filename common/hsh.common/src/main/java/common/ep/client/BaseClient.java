package common.ep.client;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Базовый rest client
 *
 * @author elf
 */
public abstract class BaseClient implements AutoCloseable {
    protected static final long DEFAULT_CONNECT_TIMEOUT_MILLISECONDS = 3000L;
    protected static final long DEFAULT_READ_TIMEOUT_MILLISECONDS = 2000L;

    private final Client client;
    private String mediaType;
    private ExecutorService executorService;

    public BaseClient(
            long connectTimeoutMilliseconds,
            long readTimeoutMilliseconds,
            String mediaType,
            HostnameVerifier sslHostnameVerifier,
            ExecutorService executorService) {
        ClientBuilder cb = ClientBuilder.newBuilder()
                .connectTimeout(connectTimeoutMilliseconds, TimeUnit.MILLISECONDS)
                .readTimeout(readTimeoutMilliseconds, TimeUnit.MILLISECONDS)
                .executorService(executorService)
                .hostnameVerifier(sslHostnameVerifier != null ?
                        sslHostnameVerifier :
                        this::defaultHostnameVerifier);
        client = cb.build();
        this.mediaType = mediaType;
        this.executorService = executorService;
    }

    @Override
    public void close(){
        client.close();
    }

    protected Client getClient() {
        return client;
    }

    public String getMediaType() {
        return mediaType;
    }

    public ExecutorService getExecutorService() {
        return executorService;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public void setExecutorService(ExecutorService executorService) {
        this.executorService = executorService;
    }

    protected boolean defaultHostnameVerifier(String string, SSLSession sslSession) {
        return true;
    }
}
