package common.ep.mapper;

import common.dto.helper.ExceptionDtoHelper;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.NotSupportedException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NotSupportedExceptionMapper implements ExceptionMapper<NotSupportedException> {
    @Context
    private HttpServletRequest request;

    @Override
    public Response toResponse(NotSupportedException exception) {
        return Response.status(Response.Status.UNSUPPORTED_MEDIA_TYPE)
                .type(ExceptionDtoHelper.getCorrectContentType(request))
                .entity(ExceptionDtoHelper.toDto(exception))
                .build();
    }
}
