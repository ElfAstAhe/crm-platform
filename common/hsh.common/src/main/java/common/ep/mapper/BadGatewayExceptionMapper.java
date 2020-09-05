package common.ep.mapper;

import common.dto.helper.ExceptionDtoHelper;
import common.exceptions.ep.BadGatewayException;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class BadGatewayExceptionMapper implements ExceptionMapper<BadGatewayException> {
    @Context
    private HttpServletRequest request;

    @Override
    public Response toResponse(BadGatewayException exception) {
        return Response.status(Response.Status.BAD_GATEWAY)
                .type(ExceptionDtoHelper.getCorrectContentType(request))
                .entity(ExceptionDtoHelper.toDto(exception))
                .build();
    }
}
