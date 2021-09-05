package org.hsh.crm.ms.users.dal.dao;

import org.hsh.crm.ms.audit.dal.entities.Role;
import javax.ejb.Stateless;

@Stateless
public class RoleDaoImpl extends BaseUsersDao<Role, String> {
    public RoleDaoImpl() {
        super(Role.class);
    }
}
