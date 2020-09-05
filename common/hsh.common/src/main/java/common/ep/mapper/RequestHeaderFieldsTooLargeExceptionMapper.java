package common.ep.mapper;

import common.dto.helper.ExceptionDtoHelper;
import common.exceptions.ep.RequestHeaderFieldsTooLargeException;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class RequestHeaderFieldsTooLargeExceptionMapper implements ExceptionMapper<RequestHeaderFieldsTooLargeException> {
    @Context
    private HttpServletRequest request;

    @Override
    public Response toResponse(RequestHeaderFieldsTooLargeException exception) {
        return Response.status(Response.Status.REQUEST_HEADER_FIELDS_TOO_LARGE)
                .type(ExceptionDtoHelper.getCorrectContentType(request))
                .entity(ExceptionDtoHelper.toDto(exception))
                .build();
    }
}
