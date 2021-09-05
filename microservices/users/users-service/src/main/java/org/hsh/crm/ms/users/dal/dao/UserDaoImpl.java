package org.hsh.crm.ms.users.dal.dao;

import org.hsh.crm.ms.audit.dal.entities.User;

import javax.ejb.Stateless;

@Stateless
public class UserDaoImpl extends BaseUsersDao<User, Long> implements UserDao {
    public UserDaoImpl() {
        super(User.class);
    }
}
