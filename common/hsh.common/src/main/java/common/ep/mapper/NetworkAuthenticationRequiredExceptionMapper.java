package common.ep.mapper;

import common.dto.helper.ExceptionDtoHelper;
import common.exceptions.ep.NetworkAuthenticationRequiredException;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NetworkAuthenticationRequiredExceptionMapper implements ExceptionMapper<NetworkAuthenticationRequiredException> {
    @Context
    private HttpServletRequest request;

    @Override
    public Response toResponse(NetworkAuthenticationRequiredException exception) {
        return Response.status(Response.Status.NETWORK_AUTHENTICATION_REQUIRED)
                .type(ExceptionDtoHelper.getCorrectContentType(request))
                .entity(ExceptionDtoHelper.toDto(exception))
                .build();
    }
}
