package org.hsh.crm.ms.users.ep.controller;

import org.hsh.crm.ms.users.dto.Role;
import org.hsh.crm.ms.users.ep.EpConstants;
import org.hsh.crm.ms.users.ep.controller.facade.RoleFacade;
import org.hsh.ms.common.ep.controller.BaseCrudController;
import org.hsh.ms.common.ep.facade.CrudFacade;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@RequestScoped
@Path(EpConstants.PATH_ROLE)
public class RoleController extends BaseCrudController<Role> {
    @Context
    private UriInfo uriInfo;

    @Inject
    private RoleFacade facade;

    @Override
    protected CrudFacade<Role> getCrudFacade() {
        return facade;
    }

    @Override
    protected UriInfo getUriInfo() {
        return uriInfo;
    }

    @Override
    protected Long getDtoId(Role instance) {
        return null;
    }

    @Override
    protected GenericEntity<List<Role>> getGenericEntity(List<Role> list) {
        return new GenericEntity<List<Role>>(list){};
    }
}
