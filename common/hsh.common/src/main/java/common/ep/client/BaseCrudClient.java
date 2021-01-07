package common.ep.client;

import common.exceptions.runtime.base.ClientRuntimeException;

import javax.net.ssl.HostnameVerifier;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Базовая реализация Crud клиента
 *
 * @param <Dto> dto class
 * @author elf
 */
public abstract class BaseCrudClient<Dto> extends BaseRestClient implements CrudClient<Dto> {
    protected static final String PATH_URI = "api/v1";
    protected static final String PATH_GET_INSTANCE = "/{id}";
    protected static final String PATH_LIST_ALL = "/list/all";
    protected static final String PATH_CREATE_INSTANCE = "";
    protected static final String PATH_EDIT_INSTANCE = "/{id}";
    protected static final String PATH_REMOVE_INSTANCE = "/{id}";

    public static final String PATH_PARAM_ID = "id";

    private final Class<? extends Dto> dtoClass;
    private final WebTarget resourceTarget;

    protected static final Logger logger = Logger.getLogger(BaseCrudClient.class.getName());

    public BaseCrudClient(String baseUri,
                          String resourcePath,
                          long connectTimeoutMilliseconds,
                          long readTimeoutMilliseconds,
                          String mediaType,
                          HostnameVerifier sslHostnameVerifier,
                          ExecutorService executorService,
                          Class<Dto> dtoClass) {
        super(baseUri,
                connectTimeoutMilliseconds,
                readTimeoutMilliseconds,
                mediaType,
                sslHostnameVerifier,
                executorService);
        resourceTarget = getBaseTarget().path(PATH_URI).path(resourcePath);
        this.dtoClass = dtoClass;
    }

    @Override
    public Dto getInstance(Object id) {
        try (Response resp = get(buildGet(id))) {
            // Если ничего
            if (resp.getStatus() == Response.Status.NO_CONTENT.getStatusCode())
                return null;

            return resp.readEntity(dtoClass);
        } catch (RuntimeException ex) {
            logger.log(Level.SEVERE, "error getInstance", ex);
            if (ex instanceof ClientRuntimeException)
                logger.log(Level.SEVERE, String.format("response body [%s]", ((ClientRuntimeException)ex).getResponseBody()));
            throw ex;
        }
    }

    @Override
    public List<Dto> listAllInstances() {
        try (Response resp = get(buildListAll())) {
            return resp.readEntity(getListGenericType());
        } catch (RuntimeException ex) {
            logger.log(Level.SEVERE, "error listAllInstances", ex);
            if (ex instanceof ClientRuntimeException)
                logger.log(Level.SEVERE, String.format("response body [%s]", ((ClientRuntimeException)ex).getResponseBody()));
            throw ex;
        }
    }

    @Override
    public Dto createInstance(Dto instance) {
        try (Response resp = post(buildCreate(), instance)) {
            return resp.readEntity(dtoClass);
        } catch (RuntimeException ex) {
            logger.log(Level.SEVERE, "error createInstance", ex);
            if (ex instanceof ClientRuntimeException)
                logger.log(Level.SEVERE, String.format("response body [%s]", ((ClientRuntimeException)ex).getResponseBody()));
            throw ex;
        }
    }

    @Override
    public Dto editInstance(Dto instance) {
        try (Response resp = put(buildEdit(getId(instance)), instance)) {
            // Если ничего
            if (resp.getStatus() == Response.Status.NO_CONTENT.getStatusCode())
                return null;

            return resp.readEntity(dtoClass);
        } catch (RuntimeException ex) {
            logger.log(Level.SEVERE, "error editInstance", ex);
            if (ex instanceof ClientRuntimeException)
                logger.log(Level.SEVERE, String.format("response body [%s]", ((ClientRuntimeException)ex).getResponseBody()));
            throw ex;
        }
    }

    @Override
    public void removeInstance(Object id) {
        try (Response resp = delete(buildRemove(id))) {
            // nothing
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "error removeInstance", ex);
            if (ex instanceof ClientRuntimeException)
                logger.log(Level.SEVERE, String.format("response body [%s]", ((ClientRuntimeException)ex).getResponseBody()));
            throw ex;
        }
    }

    @Override
    public Future<Dto> getInstanceAsync(Object id) {
        return getExecutorService().submit(() -> this.getInstance(id));
    }

    @Override
    public Future<List<Dto>> listAllAsync() {
        return getExecutorService().submit(this::listAllInstances);
    }

    @Override
    public Future<Dto> createInstanceAsync(Dto instance) {
        return getExecutorService().submit(() -> this.createInstance(instance));
    }

    @Override
    public Future<Dto> editInstanceAsync(Dto instance) {
        return getExecutorService().submit(() -> this.editInstance(instance));
    }

    @Override
    public Future<?> removeInstanceAsync(Object id) {
        return getExecutorService().submit(() -> this.removeInstance(id));
    }

    protected WebTarget getResourceTarget() {
        return resourceTarget;
    }

    protected Class<? extends Dto> getDtoClass() {
        return dtoClass;
    }

    protected abstract Long getId(Dto instance);

    protected abstract GenericType<List<Dto>> getListGenericType();

    protected abstract String getJwt();

    private RequestBuilder buildGet(Object id) {
        return new RequestBuilder(resourceTarget)
                .withPath(PATH_GET_INSTANCE)
                .withTemplate(PATH_PARAM_ID, id)
                .withMediaType(getMediaType())
                .withJwt(getJwt());
    }

    private RequestBuilder buildListAll() {
        return new RequestBuilder(resourceTarget)
                .withPath(PATH_LIST_ALL)
                .withMediaType(getMediaType())
                .withJwt(getJwt());
    }

    private RequestBuilder buildCreate() {
        return new RequestBuilder(resourceTarget)
                .withPath(PATH_CREATE_INSTANCE)
                .withMediaType(getMediaType())
                .withJwt(getJwt());
    }

    private RequestBuilder buildEdit(Object id) {
        return new RequestBuilder(resourceTarget)
                .withPath(PATH_EDIT_INSTANCE)
                .withTemplate(PATH_PARAM_ID, id)
                .withMediaType(getMediaType())
                .withJwt(getJwt());
    }

    private RequestBuilder buildRemove(Object id) {
        return new RequestBuilder(resourceTarget)
                .withPath(PATH_REMOVE_INSTANCE)
                .withTemplate(PATH_PARAM_ID, id)
                .withMediaType(getMediaType())
                .withJwt(getJwt());
    }
}
