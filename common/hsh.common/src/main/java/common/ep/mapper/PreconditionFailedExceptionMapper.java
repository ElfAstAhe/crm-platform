package common.ep.mapper;

import common.dto.helper.ExceptionDtoHelper;
import common.exceptions.ep.PreconditionFailedException;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class PreconditionFailedExceptionMapper implements ExceptionMapper<PreconditionFailedException> {
    @Context
    private HttpServletRequest request;

    @Override
    public Response toResponse(PreconditionFailedException exception) {
        return Response.status(Response.Status.PRECONDITION_FAILED)
                .type(ExceptionDtoHelper.getCorrectContentType(request))
                .entity(ExceptionDtoHelper.toDto(exception))
                .build();
    }
}
