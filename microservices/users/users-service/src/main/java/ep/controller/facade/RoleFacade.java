package ep.controller.facade;

import common.ep.facade.CrudFacade;
import dal.dao.RoleDao;
import dto.users.Role;

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
