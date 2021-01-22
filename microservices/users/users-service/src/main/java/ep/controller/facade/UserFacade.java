package ep.controller.facade;

import common.ep.facade.CrudFacade;
import dto.users.User;

import javax.enterprise.context.RequestScoped;
import java.util.List;

@RequestScoped
public class UserFacade implements CrudFacade<User> {
    @Override
    public User getInstance(Object id) {
        return null;
    }

    @Override
    public List<User> listAllInstances() {
        return null;
    }

    @Override
    public User createInstance(User instance) {
        return null;
    }

    @Override
    public User editInstance(Object id, User instance) {
        return null;
    }

    @Override
    public void removeInstance(Object id) {

    }
}
