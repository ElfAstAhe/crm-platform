package common.ep.mapper;


import common.dto.helper.ExceptionDtoHelper;
import common.exceptions.runtime.ep.GatewayTimeoutException;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class GatewayTimeoutExceptionMapper implements ExceptionMapper<GatewayTimeoutException> {
    @Context
    private HttpServletRequest request;

    @Override
    public Response toResponse(GatewayTimeoutException exception) {
        return Response.status(Response.Status.GATEWAY_TIMEOUT)
                .type(ExceptionDtoHelper.getCorrectContentType(request))
                .entity(ExceptionDtoHelper.toDto(exception))
                .build();
    }
}
