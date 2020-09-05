package common.ep.client;

import common.dto.ExceptionDto;
import common.exceptions.RsException;
import common.util.StringUtils;

import javax.net.ssl.HostnameVerifier;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.HttpHeaders;
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
 * @param <Key> key class
 * @author elf
 */
public abstract class BaseCrudClient<Dto, Key> extends BaseClient implements CrudClient<Dto, Key> {
    private static final String TEMPLATE_AUTH_VALUE = "Bearer %s";

    private final Class<? extends Dto> dtoClass;
    private final WebTarget target;

    private String jwtToken;

    private static final Logger logger = Logger.getLogger(BaseCrudClient.class.getName());

    public BaseCrudClient(String baseUri,
                          String resourcePath,
                          long connectTimeoutMilliseconds,
                          long readTimeoutMilliseconds,
                          String mediaType,
                          HostnameVerifier sslHostnameVerifier,
                          ExecutorService executor,
                          Class<Dto> dtoClass) {
        super(connectTimeoutMilliseconds, readTimeoutMilliseconds, mediaType, sslHostnameVerifier, executor);
        target = getClient().target(baseUri).path(PATH_URI).path(resourcePath);
        this.dtoClass = dtoClass;
    }

    @Override
    public Dto getInstance(Long id) throws RsException {
        String responseBody = null;
        try (Response resp = buildRequest(target.path(PATH_GET_INSTANCE).resolveTemplate(PATH_PARAM_ID, id))
                .accept(getMediaType())
                .get()) {
            // Фиксируем буфер
            resp.bufferEntity();
            responseBody = resp.readEntity(String.class);
            // Проверки
            // ..
            // Если ошибка
            if (resp.getStatus() >= Response.Status.BAD_REQUEST.getStatusCode())
                throw new RsException(Messages.REMOTE_EXCEPTION,
                        resp.getStatus(),
                        resp.readEntity(ExceptionDto.class));
            // Если ничего
            if (resp.getStatus() == Response.Status.NO_CONTENT.getStatusCode())
                return null;

            return resp.readEntity(dtoClass);
        } catch (Throwable ex) {
            logger.log(Level.SEVERE, "error getInstance", ex);
            logger.log(Level.INFO, StringUtils.formatNull("response body [%s]", responseBody));
            throw ex;
        }
    }

    @Override
    public Dto getInstanceByKey(Key key) throws RsException {
        String responseBody = null;
        try (Response resp = buildRequest(target.path(PATH_GET_INSTANCE_BY_KEY).queryParam(QUERY_PARAM_KEY, key))
                .accept(getMediaType())
                .get()) {
            // Фиксируем буфер
            resp.bufferEntity();
            responseBody = resp.readEntity(String.class);
            // Проверки
            // Если ошибка
            if (resp.getStatus() >= Response.Status.BAD_REQUEST.getStatusCode())
                throw new RsException(Messages.REMOTE_EXCEPTION,
                        resp.getStatus(),
                        resp.readEntity(ExceptionDto.class));
            // Если ничего
            if (resp.getStatus() == Response.Status.NO_CONTENT.getStatusCode())
                return null;

            return resp.readEntity(dtoClass);
        } catch (Throwable ex) {
            logger.log(Level.SEVERE, "error getInstanceByKey", ex);
            logger.log(Level.INFO, StringUtils.formatNull("response body [%s]", responseBody));
            throw ex;
        }
    }

    @Override
    public List<Dto> listAllInstances() throws RsException {
        String responseBody = null;
        try (Response resp = buildRequest(target.path(PATH_LIST_ALL))
                .accept(getMediaType())
                .get()) {
            // Фиксируем буфер
            resp.bufferEntity();
            responseBody = resp.readEntity(String.class);
            // Проверки
            // Если ошибка
            if (resp.getStatus() >= Response.Status.BAD_REQUEST.getStatusCode())
                throw new RsException(Messages.REMOTE_EXCEPTION,
                        resp.getStatus(),
                        resp.readEntity(ExceptionDto.class));

            return resp.readEntity(getListGenericType());
        } catch (Throwable ex) {
            logger.log(Level.SEVERE, "error listAllInstances", ex);
            logger.log(Level.INFO, StringUtils.formatNull("response body [%s]", responseBody));
            throw ex;
        }
    }

    @Override
    public List<Dto> listInstances(int fromRow, int rowCount) throws RsException {
        String responseBody = null;
        try (Response resp = buildRequest(target.path(PATH_LIST)
                .queryParam(QUERY_PARAM_FROM_ROW, fromRow)
                .queryParam(QUERY_PARAM_ROW_COUNT, rowCount))
                .accept(getMediaType())
                .get()) {
            // Фиксируем буфер
            resp.bufferEntity();
            responseBody = resp.readEntity(String.class);
            // Проверки
            // Если ошибка
            if (resp.getStatus() >= Response.Status.BAD_REQUEST.getStatusCode())
                throw new RsException(Messages.REMOTE_EXCEPTION,
                        resp.getStatus(),
                        resp.readEntity(ExceptionDto.class));

            return resp.readEntity(getListGenericType());
        } catch (Throwable ex) {
            logger.log(Level.SEVERE, "error listInstances", ex);
            logger.log(Level.INFO, StringUtils.formatNull("response body [%s]", responseBody));
            throw ex;
        }
    }

    @Override
    public Dto createInstance(Dto instance) throws RsException {
        String responseBody = null;
        try (Response resp = buildRequest(target.path(PATH_CREATE_INSTANCE))
                .accept(getMediaType())
                .post(Entity.entity(instance, getMediaType()))) {
            // Фиксируем буфер
            resp.bufferEntity();
            responseBody = resp.readEntity(String.class);
            // Проверки
            // Если ошибка
            if (resp.getStatus() >= Response.Status.BAD_REQUEST.getStatusCode())
                throw new RsException(Messages.REMOTE_EXCEPTION,
                        resp.getStatus(),
                        resp.readEntity(ExceptionDto.class));

            return resp.readEntity(dtoClass);
        } catch (Throwable ex) {
            logger.log(Level.SEVERE, "error createInstance", ex);
            logger.log(Level.INFO, StringUtils.formatNull("response body [%s]", responseBody));
            throw ex;
        }
    }

    @Override
    public Dto editInstance(Dto instance) throws RsException {
        String responseBody = null;
        try (Response resp = buildRequest(target.path(PATH_EDIT_INSTANCE).resolveTemplate(PATH_PARAM_ID, getId(instance)))
                .accept(getMediaType())
                .put(Entity.entity(instance, getMediaType()))) {
            // Фиксируем буфер
            resp.bufferEntity();
            responseBody = resp.readEntity(String.class);
            // Проверки
            // ..
            // Если ошибка
            if (resp.getStatus() >= Response.Status.BAD_REQUEST.getStatusCode())
                throw new RsException(Messages.REMOTE_EXCEPTION,
                        resp.getStatus(),
                        resp.readEntity(ExceptionDto.class));
            // Если ничего
            if (resp.getStatus() == Response.Status.NO_CONTENT.getStatusCode())
                return null;

            return resp.readEntity(dtoClass);
        } catch (Throwable ex) {
            logger.log(Level.SEVERE, "error editInstance", ex);
            logger.log(Level.INFO, StringUtils.formatNull("response body [%s]", responseBody));
            throw ex;
        }
    }

    @Override
    public void removeInstance(Long id) throws RsException {
        String responseBody = null;
        try (Response resp = buildRequest(target.path(PATH_REMOVE_INSTANCE).resolveTemplate(PATH_PARAM_ID, id))
                .accept(getMediaType())
                .delete()) {
            // Фиксируем буфер
            resp.bufferEntity();
            responseBody = resp.readEntity(String.class);
            // Проверки
            // Если ошибка
            if (resp.getStatus() >= Response.Status.BAD_REQUEST.getStatusCode())
                throw new RsException(Messages.REMOTE_EXCEPTION,
                        resp.getStatus(),
                        resp.readEntity(ExceptionDto.class));
        } catch (Throwable ex) {
            logger.log(Level.SEVERE, "error removeInstance", ex);
            logger.log(Level.INFO, StringUtils.formatNull("response body [%s]", responseBody));
            throw ex;
        }
    }

    @Override
    public Future<Dto> getInstanceAsync(Long id) {
        return getExecutor().submit(() -> this.getInstance(id));
    }

    @Override
    public Future<Dto> getInstanceByKeyAsync(Key key) {
        return getExecutor().submit(() -> this.getInstanceByKey(key));
    }

    @Override
    public Future<List<Dto>> listAllAsync() {
        return getExecutor().submit(this::listAllInstances);
    }

    @Override
    public Future<List<Dto>> listInstancesAsync(int fromRow, int rowCount) {
        return getExecutor().submit(() -> this.listInstances(fromRow, rowCount));
    }

    @Override
    public Future<Dto> createInstanceAsync(Dto instance) {
        return getExecutor().submit(() -> this.createInstance(instance));
    }

    @Override
    public Future<Dto> editInstanceAsync(Dto instance) {
        return getExecutor().submit(() -> this.editInstance(instance));
    }

    @Override
    public Future<?> removeInstanceAsync(Long id) {
        return getExecutor().submit(() -> {
            try {
                this.removeInstance(id);
            } catch (RsException ex) {
                //Logger.getLogger(BaseCrudClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    public CrudClient<Dto, Key> withJwtToken(String jwtToken) {
        setJwtToken(jwtToken);
        return this;
    }

    protected String getJwtToken() {
        return jwtToken;
    }

    protected void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    protected WebTarget getTarget() {
        return target;
    }

    protected Class<? extends Dto> getDtoClass() {
        return dtoClass;
    }

    protected Invocation.Builder buildRequest(WebTarget webTarget) {
        // Без авторизации
        if (org.apache.commons.lang3.StringUtils.isBlank(jwtToken))
            return webTarget.request();

        // С авторизацией
        return webTarget.request().header(HttpHeaders.AUTHORIZATION, buildAuthBearer(jwtToken));
    }

    protected abstract Long getId(Dto instance);

    protected abstract GenericType<List<Dto>> getListGenericType();

    private String buildAuthBearer(String jwtToken) {
        return String.format(TEMPLATE_AUTH_VALUE, jwtToken);
    }
}
