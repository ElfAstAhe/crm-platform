package common.ep.mapper;

import common.dto.helper.ExceptionDtoHelper;
import common.exceptions.ep.NotImplementedException;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NotImplementedExceptionMapper implements ExceptionMapper<NotImplementedException> {
    @Context
    private HttpServletRequest request;

    @Override
    public Response toResponse(NotImplementedException exception) {
        return Response.status(Response.Status.NOT_IMPLEMENTED)
                .type(ExceptionDtoHelper.getCorrectContentType(request))
                .entity(ExceptionDtoHelper.toDto(exception))
                .build();
    }
}
