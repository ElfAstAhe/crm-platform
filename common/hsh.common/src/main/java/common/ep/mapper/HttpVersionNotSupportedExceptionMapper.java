package common.ep.mapper;

import common.dto.helper.ExceptionDtoHelper;
import common.exceptions.runtime.ep.HttpVersionNotSupportedException;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class HttpVersionNotSupportedExceptionMapper implements ExceptionMapper<HttpVersionNotSupportedException> {
    @Context
    private HttpServletRequest request;

    @Override
    public Response toResponse(HttpVersionNotSupportedException exception) {
        return Response.status(Response.Status.HTTP_VERSION_NOT_SUPPORTED)
                .type(ExceptionDtoHelper.getCorrectContentType(request))
                .entity(ExceptionDtoHelper.toDto(exception))
                .build();
    }
}
