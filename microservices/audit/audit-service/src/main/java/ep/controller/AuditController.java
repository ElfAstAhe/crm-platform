package ep.controller;

import ep.controller.facade.AuditFacade;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * Контроллер отчётов
 */
@Path("audit")
public class AuditController {
    @Inject
    private AuditFacade facade;

    @GET
    public Response test() {
        return Response.ok().build();
    }
}
