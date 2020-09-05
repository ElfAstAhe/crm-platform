package common.ep.mapper;

import common.dto.helper.ExceptionDtoHelper;
import common.exceptions.ep.TooManyRequestsException;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class TooManyRequestsExceptionMapper implements ExceptionMapper<TooManyRequestsException> {
    @Context
    private HttpServletRequest request;

    @Override
    public Response toResponse(TooManyRequestsException exception) {
        return Response.status(Response.Status.TOO_MANY_REQUESTS)
                .type(ExceptionDtoHelper.getCorrectContentType(request))
                .entity(ExceptionDtoHelper.toDto(exception))
                .build();
    }
}
