package ep.controller;

import common.web.MimeTypes;
import ep.dto.TestDto;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@RequestScoped
@Path("test")
public class TestController {

    @GET
    @Produces({MimeTypes.Application.JSON, MimeTypes.Application.XML})
    public Response test() {
        return Response.ok(new TestDto("test", "testing :-)"))
                .build();
    }
}
