package common.ep.mapper;

import common.dto.helper.ExceptionDtoHelper;
import common.exceptions.runtime.ep.ExpectationFailedException;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ExpectationFailedExceptionMapper implements ExceptionMapper<ExpectationFailedException> {
    @Context
    private HttpServletRequest request;

    @Override
    public Response toResponse(ExpectationFailedException exception) {
        return Response.status(Response.Status.EXPECTATION_FAILED)
                .type(ExceptionDtoHelper.getCorrectContentType(request))
                .entity(ExceptionDtoHelper.toDto(exception))
                .build();
    }
}
