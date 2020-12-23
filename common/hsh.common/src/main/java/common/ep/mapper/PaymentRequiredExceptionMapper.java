package common.ep.mapper;

import common.dto.helper.ExceptionDtoHelper;
import common.exceptions.runtime.ep.PaymentRequiredException;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class PaymentRequiredExceptionMapper implements ExceptionMapper<PaymentRequiredException> {
    @Context
    private HttpServletRequest request;

    @Override
    public Response toResponse(PaymentRequiredException exception) {
        return Response.status(Response.Status.PAYMENT_REQUIRED)
                .type(ExceptionDtoHelper.getCorrectContentType(request))
                .entity(ExceptionDtoHelper.toDto(exception))
                .build();
    }
}
