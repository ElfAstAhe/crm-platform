package org.hsh.crm.ms.users.dal.dao;

import org.hsh.crm.ms.users.dal.entities.Role;

import javax.ejb.Stateless;

@Stateless
public class RoleDaoImpl extends BaseUsersDao<Role, Long> {
    public RoleDaoImpl() {
        super(Role.class);
    }
}
