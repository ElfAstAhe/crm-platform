package common.ep.mapper;

import common.dto.helper.ExceptionDtoHelper;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.NotAcceptableException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NotAcceptableExceptionMapper implements ExceptionMapper<NotAcceptableException> {
    @Context
    private HttpServletRequest request;

    @Override
    public Response toResponse(NotAcceptableException exception) {
        return Response.status(Response.Status.NOT_ACCEPTABLE)
                .type(ExceptionDtoHelper.getCorrectContentType(request))
                .entity(ExceptionDtoHelper.toDto(exception))
                .build();
    }
}
