package common.ep.mapper;

import common.dto.helper.ExceptionDtoHelper;
import common.exceptions.ep.ResourceGoneException;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ResourceGoneExceptionMapper implements ExceptionMapper<ResourceGoneException> {
    @Context
    private HttpServletRequest request;

    @Override
    public Response toResponse(ResourceGoneException exception) {
        return Response.status(Response.Status.GONE)
                .type(ExceptionDtoHelper.getCorrectContentType(request))
                .entity(ExceptionDtoHelper.toDto(exception))
                .build();
    }
}
