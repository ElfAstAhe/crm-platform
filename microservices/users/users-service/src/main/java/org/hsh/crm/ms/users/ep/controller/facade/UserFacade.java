package org.hsh.crm.ms.users.ep.controller.facade;

import org.hsh.crm.ms.users.dto.User;
import org.hsh.ms.common.ep.facade.CrudFacade;

import javax.enterprise.context.RequestScoped;
import java.util.List;

@RequestScoped
public class UserFacade implements CrudFacade<User> {
    @Override
    public User get(Object o) {
        return null;
    }

    @Override
    public List<User> listAll() {
        return null;
    }

    @Override
    public User create(User user) {
        return null;
    }

    @Override
    public User edit(Object o, User user) {
        return null;
    }

    @Override
    public void remove(Object o) {

    }
}
