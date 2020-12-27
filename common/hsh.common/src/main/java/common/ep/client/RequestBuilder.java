package common.ep.client;

import org.jooq.tools.StringUtils;

import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class RequestBuilder {
    private WebTarget target;

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

    public RequestBuilder withPathTemplate(String pathTemplate, Object value) {
        setPathTemplate(pathTemplate, value);
        return this;
    }

    public RequestBuilder withPathTemplates(Map<String, Object> pathTemplates) {
        pathTemplates.forEach(this::setPathTemplate);
        return this;
    }

    public RequestBuilder withPathAndTemplates(Map<String, Object> pathAndTemplates) {
        pathAndTemplates.forEach((key, value) -> {
            if (value == null) {
                setPath(key);
            } else {
                setPathTemplate(key, value);
            }
        });
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

    public Invocation.Builder build() {
        return target.request();
    }

    private void setPath(String path) {
        if (path == null)
            throw new IllegalArgumentException("path can't be null");

        target = target.path(path);
    }

    private void setPathTemplate(String pathTemplate, Object value) {
        if (StringUtils.isBlank(pathTemplate))
            throw new IllegalArgumentException("empty path template");
        if (value == null)
            throw new IllegalArgumentException("nullable path template value");

        target = target.resolveTemplate(pathTemplate, value);
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
