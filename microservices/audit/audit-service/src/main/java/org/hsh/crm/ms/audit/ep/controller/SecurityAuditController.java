package org.hsh.crm.ms.audit.ep.controller;

import org.hsh.crm.ms.audit.dto.SecurityAudit;
import org.hsh.crm.ms.audit.ep.controller.facade.SecurityAuditFacade;
import org.hsh.ms.common.ep.controller.BaseCrudController;
import org.hsh.ms.common.ep.facade.CrudFacade;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

@RequestScoped
@Path("securityAudit")
public class SecurityAuditController extends BaseCrudController<SecurityAudit> {
    @Context
    private UriInfo uriInfo;

    @Inject
    private SecurityAuditFacade facade;

    @Override
    protected CrudFacade<SecurityAudit> getCrudFacade() {
        return facade;
    }

    @Override
    protected UriInfo getUriInfo() {
        return uriInfo;
    }

    @Override
    protected Object getDtoId(SecurityAudit dto) {
        return dto.getId();
    }
}
