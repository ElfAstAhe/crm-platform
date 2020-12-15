package ep.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * Контроллер отчётов
 */
@Path("audit")
public class AuditController {

    @GET
    public Response test() {
        return Response.ok().build();
    }
}
