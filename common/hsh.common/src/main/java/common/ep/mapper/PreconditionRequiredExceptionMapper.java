package common.ep.mapper;

import common.dto.helper.ExceptionDtoHelper;
import common.exceptions.ep.PreconditionRequiredException;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class PreconditionRequiredExceptionMapper implements ExceptionMapper<PreconditionRequiredException> {
    @Context
    private HttpServletRequest request;

    @Override
    public Response toResponse(PreconditionRequiredException exception) {
        return Response.status(Response.Status.PRECONDITION_REQUIRED)
                .type(ExceptionDtoHelper.getCorrectContentType(request))
                .entity(ExceptionDtoHelper.toDto(exception))
                .build();
    }
}
