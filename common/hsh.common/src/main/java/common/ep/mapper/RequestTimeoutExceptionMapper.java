package common.ep.mapper;

import common.dto.helper.ExceptionDtoHelper;
import common.exceptions.ep.RequestTimeoutException;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class RequestTimeoutExceptionMapper implements ExceptionMapper<RequestTimeoutException> {
    @Context
    private HttpServletRequest request;

    @Override
    public Response toResponse(RequestTimeoutException exception) {
        return Response.status(Response.Status.REQUEST_TIMEOUT)
                .type(ExceptionDtoHelper.getCorrectContentType(request))
                .entity(ExceptionDtoHelper.toDto(exception))
                .build();
    }
}
