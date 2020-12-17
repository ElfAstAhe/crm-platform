package ep.controller;

import common.ep.controller.BaseCrudController;
import common.ep.facade.CrudFacade;
import dto.audit.DataAudit;
import ep.controller.facade.DataAuditFacade;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

/**
 * crud dataAudit
 */
@RequestScoped
@Path("dataAudit")
public class DataAuditController extends BaseCrudController<DataAudit> {
    @Context
    private UriInfo uriInfo;

    @Inject
    private DataAuditFacade facade;

    @Override
    public Response listAllInstances() {
        return Response.status(Response.Status.GONE)
                .build();
    }

    @Override
    protected CrudFacade<DataAudit> getCrudFacade() {
        return facade;
    }

    @Override
    protected UriInfo getUriInfo() {
        return uriInfo;
    }

    @Override
    protected Long getDtoId(DataAudit instance) {
        return instance.getId();
    }

    @Override
    protected GenericEntity<List<DataAudit>> getGenericEntity(List<DataAudit> list) {
        return new GenericEntity<List<DataAudit>>(list){};
    }
}
