package org.hsh.crm.ms.users.ep.controller.facade;

import org.hsh.crm.ms.users.dto.UserDto;
import org.hsh.ms.common.ep.facade.CrudFacade;

import javax.enterprise.context.RequestScoped;
import java.util.List;

@RequestScoped
public class UserFacade implements CrudFacade<UserDto> {
    @Override
    public UserDto get(Object o) {
        return null;
    }

    @Override
    public List<UserDto> listAll() {
        return null;
    }

    @Override
    public UserDto create(UserDto user) {
        return null;
    }

    @Override
    public UserDto edit(Object o, UserDto user) {
        return null;
    }

    @Override
    public void remove(Object o) {

    }
}
