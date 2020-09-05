package common.ep.mapper;

import common.dto.helper.ExceptionDtoHelper;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NotAuthorizedExceptionMapper implements ExceptionMapper<NotAuthorizedException> {
    @Context
    private HttpServletRequest request;

    @Override
    public Response toResponse(NotAuthorizedException exception) {
        return Response.status(Response.Status.UNAUTHORIZED)
                .type(ExceptionDtoHelper.getCorrectContentType(request))
                .entity(ExceptionDtoHelper.toDto(exception))
                .build();
    }
}
