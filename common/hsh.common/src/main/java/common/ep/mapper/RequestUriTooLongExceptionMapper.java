package common.ep.mapper;

import common.dto.helper.ExceptionDtoHelper;
import common.exceptions.runtime.ep.RequestUriTooLongException;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class RequestUriTooLongExceptionMapper implements ExceptionMapper<RequestUriTooLongException> {
    @Context
    private HttpServletRequest request;

    @Override
    public Response toResponse(RequestUriTooLongException exception) {
        return Response.status(Response.Status.REQUEST_URI_TOO_LONG)
                .type(ExceptionDtoHelper.getCorrectContentType(request))
                .entity(ExceptionDtoHelper.toDto(exception))
                .build();
    }
}
