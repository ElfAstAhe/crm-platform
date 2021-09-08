package org.hsh.crm.ms.users.ep.controller;

import org.hsh.crm.ms.users.dto.User;
import org.hsh.crm.ms.users.ep.EpConstants;
import org.hsh.crm.ms.users.ep.controller.facade.UserFacade;
import org.hsh.ms.common.ep.controller.BaseCrudController;
import org.hsh.ms.common.ep.facade.CrudFacade;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

@RequestScoped
@Path(EpConstants.PATH_USER)
public class UserController extends BaseCrudController<User> {
    @Context
    private UriInfo uriInfo;

    @Inject
    private UserFacade facade;

    @Override
    protected CrudFacade<User> getCrudFacade() {
        return facade;
    }

    @Override
    protected UriInfo getUriInfo() {
        return uriInfo;
    }

    @Override
    protected Object getDtoId(User dto) {
        return dto.getId();
    }
}
