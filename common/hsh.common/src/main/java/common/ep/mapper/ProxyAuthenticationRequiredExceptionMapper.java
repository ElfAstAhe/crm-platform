package common.ep.mapper;

import common.dto.helper.ExceptionDtoHelper;
import common.exceptions.ep.ProxyAuthenticationRequiredException;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ProxyAuthenticationRequiredExceptionMapper implements ExceptionMapper<ProxyAuthenticationRequiredException> {
    @Context
    private HttpServletRequest request;

    @Override
    public Response toResponse(ProxyAuthenticationRequiredException exception) {
        return Response.status(Response.Status.PROXY_AUTHENTICATION_REQUIRED)
                .type(ExceptionDtoHelper.getCorrectContentType(request))
                .entity(ExceptionDtoHelper.toDto(exception))
                .build();
    }
}
