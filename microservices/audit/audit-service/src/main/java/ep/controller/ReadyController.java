package ep.controller;

import common.app.AppInitializer;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * readyness controller
 *
 * @author elf
 */
@RequestScoped
@Path("ready")
public class ReadyController {

    @EJB
    private AppInitializer initializer;

    @GET
    public Response getReadyInfo() {
        if (initializer.isReady())
            return Response.ok().build();
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
