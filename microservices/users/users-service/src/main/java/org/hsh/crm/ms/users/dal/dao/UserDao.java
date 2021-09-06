package org.hsh.crm.ms.users.dal.dao;

import org.hsh.common.dal.dao.CrudDao;
import org.hsh.crm.ms.users.dal.entities.User;

import javax.ejb.Local;

@Local
public interface UserDao extends CrudDao<User, Long> {
}
