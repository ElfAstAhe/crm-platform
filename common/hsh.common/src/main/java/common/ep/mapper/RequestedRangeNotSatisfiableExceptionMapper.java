package common.ep.mapper;

import common.dto.helper.ExceptionDtoHelper;
import common.exceptions.runtime.ep.RequestedRangeNotSatisfiableException;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class RequestedRangeNotSatisfiableExceptionMapper implements ExceptionMapper<RequestedRangeNotSatisfiableException> {
    @Context
    private HttpServletRequest request;

    @Override
    public Response toResponse(RequestedRangeNotSatisfiableException exception) {
        return Response.status(Response.Status.REQUESTED_RANGE_NOT_SATISFIABLE)
                .type(ExceptionDtoHelper.getCorrectContentType(request))
                .entity(ExceptionDtoHelper.toDto(exception))
                .build();
    }
}
