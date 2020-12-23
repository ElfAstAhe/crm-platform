package common.ep.mapper;

import common.dto.helper.ExceptionDtoHelper;
import common.exceptions.runtime.ep.LengthRequiredException;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class LengthRequiredExceptionMapper implements ExceptionMapper<LengthRequiredException> {
    @Context
    private HttpServletRequest request;

    @Override
    public Response toResponse(LengthRequiredException exception) {
        return Response.status(Response.Status.LENGTH_REQUIRED)
                .type(ExceptionDtoHelper.getCorrectContentType(request))
                .entity(ExceptionDtoHelper.toDto(exception))
                .build();
    }
}
