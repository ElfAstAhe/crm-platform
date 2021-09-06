package org.hsh.crm.ms.users.ep.controller.facade;

import org.hsh.crm.ms.users.dal.dao.RoleDao;
import org.hsh.crm.ms.users.dto.Role;
import org.hsh.ms.common.ep.facade.CrudFacade;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.List;

@RequestScoped
@Named
public class RoleFacade implements CrudFacade<Role> {
    @EJB
    private RoleDao daoRole;

    @Override
    public Role getInstance(Object id) {
        return null;
    }

    @Override
    public List<Role> listAllInstances() {
        return null;
    }

    @Override
    public Role createInstance(Role instance) {
        return null;
    }

    @Override
    public Role editInstance(Object id, Role instance) {
        return null;
    }

    @Override
    public void removeInstance(Object id) {

    }
}
