package ep.controller;

import common.ep.controller.BaseCrudController;
import common.ep.facade.CrudFacade;
import dto.audit.SecurityAudit;
import ep.controller.facade.SecurityAuditFacade;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@RequestScoped
@Path("securityAudit")
public class SecurityAuditController extends BaseCrudController<SecurityAudit> {
    @Context
    private UriInfo uriInfo;

    @Inject
    private SecurityAuditFacade facade;

    @Override
    public Response listAllInstances() {
        return Response.status(Response.Status.GONE)
                .build();
    }

    @Override
    protected CrudFacade<SecurityAudit> getCrudFacade() {
        return facade;
    }

    @Override
    protected UriInfo getUriInfo() {
        return uriInfo;
    }

    @Override
    protected Long getDtoId(SecurityAudit instance) {
        return instance.getId();
    }

    @Override
    protected GenericEntity<List<SecurityAudit>> getGenericEntity(List<SecurityAudit> list) {
        return new GenericEntity<List<SecurityAudit>>(list){};
    }
}
