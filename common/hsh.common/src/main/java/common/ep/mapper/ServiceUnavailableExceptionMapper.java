package common.ep.mapper;

import common.dto.helper.ExceptionDtoHelper;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.ServiceUnavailableException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ServiceUnavailableExceptionMapper implements ExceptionMapper<ServiceUnavailableException> {
    @Context
    private HttpServletRequest request;

    @Override
    public Response toResponse(ServiceUnavailableException exception) {
        return Response.status(Response.Status.SERVICE_UNAVAILABLE)
                .type(ExceptionDtoHelper.getCorrectContentType(request))
                .entity(ExceptionDtoHelper.toDto(exception))
                .build();
    }
}
