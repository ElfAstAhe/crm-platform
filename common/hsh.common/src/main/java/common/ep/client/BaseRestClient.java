package common.ep.client;

import common.exceptions.runtime.base.ClientRuntimeException;

import javax.net.ssl.HostnameVerifier;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.util.concurrent.ExecutorService;

public class BaseRestClient extends BaseClient {
    private final WebTarget baseTarget;

    public BaseRestClient(String baseUri,
                          long connectTimeoutMilliseconds,
                          long readTimeoutMilliseconds,
                          String mediaType,
                          HostnameVerifier sslHostnameVerifier,
                          ExecutorService executorService) {
        super(connectTimeoutMilliseconds,
                readTimeoutMilliseconds,
                mediaType,
                sslHostnameVerifier,
                executorService);
        baseTarget = getClient().target(baseUri);
    }

    protected Response get(RequestBuilder requestBuilder) {
        Response result = null;
        try {
            // запрос
            result = requestBuilder.build()
                    .get();
            // фиксируем буфер
            result.bufferEntity();
            // ошибка по статусу
            if (result.getStatus() >= Response.Status.BAD_REQUEST.getStatusCode())
                throw new ClientRuntimeException(Messages.REMOTE_EXCEPTION,
                        result.getStatus(),
                        result.readEntity(String.class));

            return result;
        } catch (ClientRuntimeException ex) {
            if (result != null)
                result.close();
            throw ex;
        }
    }

    protected Response post(RequestBuilder requestBuilder, Object instance) {
        Response result = null;
        try  {
            result = requestBuilder.build()
                    .post(Entity.entity(instance, getMediaType()));
            // Фиксируем буфер
            result.bufferEntity();
            // Если ошибка
            if (result.getStatus() >= Response.Status.BAD_REQUEST.getStatusCode())
                throw new ClientRuntimeException(Messages.REMOTE_EXCEPTION,
                        result.getStatus(),
                        result.readEntity(String.class));

            return result;
        } catch (ClientRuntimeException ex) {
            if (result != null)
                result.close();
            throw ex;
        }
    }

    protected Response put(RequestBuilder requestBuilder, Object instance) {
        Response result = null;
        try  {
            result = requestBuilder.build()
                    .put(Entity.entity(instance, getMediaType()));
            // Фиксируем буфер
            result.bufferEntity();
            // Если ошибка
            if (result.getStatus() >= Response.Status.BAD_REQUEST.getStatusCode())
                throw new ClientRuntimeException(Messages.REMOTE_EXCEPTION,
                        result.getStatus(),
                        result.readEntity(String.class));

            return result;
        } catch (ClientRuntimeException ex) {
            if (result != null)
                result.close();
            throw ex;
        }
    }

    protected Response delete(RequestBuilder requestBuilder) {
        Response result = null;
        try  {
            result = requestBuilder.build()
                    .delete();
            // Фиксируем буфер
            result.bufferEntity();
            // Если ошибка
            if (result.getStatus() >= Response.Status.BAD_REQUEST.getStatusCode())
                throw new ClientRuntimeException(Messages.REMOTE_EXCEPTION,
                        result.getStatus(),
                        result.readEntity(String.class));

            return result;
        } catch (ClientRuntimeException ex) {
            if (result != null)
                result.close();
            throw ex;
        }
    }

    protected WebTarget getBaseTarget() {
        return baseTarget;
    }
}
