package common.ep.client;

import org.jooq.tools.StringUtils;

import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.HttpHeaders;
import java.util.List;
import java.util.Map;

public class RequestBuilder {
    private static final String TEMPLATE_AUTH_BEARER = "Bearer %s";
    private WebTarget target;

    private String jwt;
    private String mediaType;

    public RequestBuilder(WebTarget target) {
        if (target == null)
            throw new IllegalArgumentException("target is null");
        this.target = target;
    }

    public RequestBuilder withPath(String path) {
        setPath(path);
        return this;
    }

    public RequestBuilder withPaths(List<String> paths) {
        paths.forEach(this::setPath);
        return this;
    }

    public RequestBuilder withTemplate(String name, Object value) {
        setTemplate(name, value);
        return this;
    }

    public RequestBuilder withTemplates(Map<String, Object> templates) {
        templates.forEach(this::setTemplate);
        return this;
    }

    public RequestBuilder withQueryParam(String param, Object value) {
        setQueryParam(param, value);
        return this;
    }

    public RequestBuilder withQueryParams(Map<String, Object> params) {
        params.forEach(this::setQueryParam);
        return this;
    }

    public RequestBuilder withMatrixParam(String param, Object value) {
        setMatrixParam(param, value);
        return this;
    }

    public RequestBuilder withMatrixParams(Map<String, Object> params) {
        params.forEach(this::setMatrixParam);
        return this;
    }

    public RequestBuilder withMediaType(String mediaType) {
        this.mediaType = mediaType;
        return this;
    }

    public RequestBuilder withJwt(String jwt) {
        this.jwt = jwt;
        return this;
    }

    public Invocation.Builder build() {
        Invocation.Builder request = target.request();
        // определяем media type
        if (!StringUtils.isBlank(mediaType))
            request = request.accept(mediaType);
        // определяем jwt token
        if (!StringUtils.isBlank(jwt))
            request = request.header(HttpHeaders.AUTHORIZATION, buildAuthBearer(jwt));

        return request;
    }

    protected String buildAuthBearer(String jwtToken) {
        return String.format(TEMPLATE_AUTH_BEARER, jwtToken);
    }

    private void setPath(String path) {
        if (path == null)
            throw new IllegalArgumentException("path can't be null");

        target = target.path(path);
    }

    private void setTemplate(String name, Object value) {
        if (StringUtils.isBlank(name))
            throw new IllegalArgumentException("empty template");
        if (value == null)
            throw new IllegalArgumentException("nullable template value");

        target = target.resolveTemplate(name, value);
    }

    private void setQueryParam(String param, Object value) {
        if (StringUtils.isBlank(param))
            throw new IllegalArgumentException("empty param");
        if (value == null)
            throw new IllegalArgumentException("nullable query param value");

        target = target.queryParam(param, value);
    }

    private void setMatrixParam(String param, Object value) {
        if (StringUtils.isBlank(param))
            throw new IllegalArgumentException("empty param");
        if (value == null)
            throw new IllegalArgumentException("nullable matrix param value");

        target = target.matrixParam(param, value);
    }
}
