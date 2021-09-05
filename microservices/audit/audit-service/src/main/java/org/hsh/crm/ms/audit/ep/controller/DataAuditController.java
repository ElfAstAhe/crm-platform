package org.hsh.crm.ms.audit.ep.controller;

import org.hsh.crm.ms.audit.dto.DataAudit;
import org.hsh.crm.ms.audit.ep.controller.facade.DataAuditFacade;
import org.hsh.ms.common.ep.controller.BaseCrudController;
import org.hsh.ms.common.ep.facade.CrudFacade;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

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
    protected CrudFacade<DataAudit> getCrudFacade() {
        return facade;
    }

    @Override
    protected UriInfo getUriInfo() {
        return uriInfo;
    }

    @Override
    protected Object getDtoId(DataAudit dto) {
        return dto.getId();
    }
}
