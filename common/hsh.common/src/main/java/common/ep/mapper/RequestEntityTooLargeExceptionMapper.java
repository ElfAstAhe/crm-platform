package common.ep.mapper;

import common.dto.helper.ExceptionDtoHelper;
import common.exceptions.runtime.ep.RequestEntityTooLargeException;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class RequestEntityTooLargeExceptionMapper implements ExceptionMapper<RequestEntityTooLargeException> {
    @Context
    private HttpServletRequest request;

    @Override
    public Response toResponse(RequestEntityTooLargeException exception) {
        return Response.status(Response.Status.REQUEST_ENTITY_TOO_LARGE)
                .type(ExceptionDtoHelper.getCorrectContentType(request))
                .entity(ExceptionDtoHelper.toDto(exception))
                .build();
    }
}
