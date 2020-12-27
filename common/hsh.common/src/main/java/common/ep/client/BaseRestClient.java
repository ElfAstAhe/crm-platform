package common.ep.client;

import common.dto.ExceptionDto;
import common.exceptions.runtime.base.ClientRuntimeException;

import javax.net.ssl.HostnameVerifier;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.util.Map;
import java.util.concurrent.ExecutorService;

public class BaseRestClient extends BaseClient {
    private final WebTarget target;

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
        target = getClient().target(baseUri);
    }

    public Response get(Map<String, Object> pathsAndTemplates,
                        Map<String, Object> queryParams) {
        Response result = null;
        try {
            // запрос
            result = new RequestBuilder(target)
                    .withPathAndTemplates(pathsAndTemplates)
                    .withQueryParams(queryParams)
                    .build()
                    .accept(getMediaType())
                    .get();
            // фиксируем буфер
            result.bufferEntity();
            // ошибка по статусу
            if (result.getStatus() >= Response.Status.BAD_REQUEST.getStatusCode())
                throw new ClientRuntimeException(Messages.REMOTE_EXCEPTION,
                        result.getStatus(),
                        result.readEntity(ExceptionDto.class));

            return result;
        } catch (ClientRuntimeException ex) {
            if (result != null)
                result.close();
            throw ex;
        }
    }

    public Response post() {
        return null;
    }

    public Response put() {
        return null;
    }

    public Response delete() {
        return null;
    }
}
