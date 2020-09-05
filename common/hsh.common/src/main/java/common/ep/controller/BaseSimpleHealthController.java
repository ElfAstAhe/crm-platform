package common.ep.controller;

import common.dto.HealthStatus;
import common.web.MimeTypes;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 * base class of app health controller
 *
 * @author elf
 */
public abstract class BaseSimpleHealthController {
    @GET
    @Produces({MimeTypes.Application.JSON, MimeTypes.Application.XML})
    public Response getHealthStatus() {
        return Response.ok().entity(new HealthStatus()).build();
    }
}
