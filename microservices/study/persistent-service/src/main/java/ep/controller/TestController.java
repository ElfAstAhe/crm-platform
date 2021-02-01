package ep.controller;

import common.ep.controller.BaseCrudController;
import common.ep.facade.CrudFacade;
import common.web.MimeTypes;
import ep.controller.facade.TestFacade;
import ep.dto.TestDto;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@RequestScoped
@Path("test")
public class TestController extends BaseCrudController<TestDto> {
    @Context
    private UriInfo uriInfo;

    @Inject
    private TestFacade facade;

    @Override
    protected CrudFacade<TestDto> getCrudFacade() {
        return null;
    }

    @Override
    protected UriInfo getUriInfo() {
        return uriInfo;
    }

    @Override
    protected Long getDtoId(TestDto instance) {
        return instance.getId();
    }

    @Override
    protected GenericEntity<List<TestDto>> getGenericEntity(List<TestDto> list) {
        return new GenericEntity<List<TestDto>>(list){};
    }
}
