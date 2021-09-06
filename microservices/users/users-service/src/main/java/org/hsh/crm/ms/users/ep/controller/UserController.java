package org.hsh.crm.ms.users.ep.controller;

import org.hsh.crm.ms.users.dto.User;
import org.hsh.crm.ms.users.ep.controller.facade.UserFacade;
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
@Path("/user")
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
    protected Long getDtoId(User instance) {
        return null;
    }

    @Override
    protected GenericEntity<List<User>> getGenericEntity(List<User> list) {
        return new GenericEntity<List<User>>(list){};
    }
}
