package common.ep.mapper;

import common.dto.helper.ExceptionDtoHelper;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InternalServerErrorExceptionMapper implements ExceptionMapper<InternalServerErrorException> {
    @Context
    private HttpServletRequest request;

    @Override
    public Response toResponse(InternalServerErrorException exception) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .type(ExceptionDtoHelper.getCorrectContentType(request))
                .entity(ExceptionDtoHelper.toDto(exception))
                .build();
    }
}
