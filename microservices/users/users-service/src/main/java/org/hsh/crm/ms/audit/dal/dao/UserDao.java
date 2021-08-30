package org.hsh.crm.ms.audit.dal.dao;

import common.dal.dao.CrudDao;
import org.hsh.crm.ms.audit.dal.entities.User;

import javax.ejb.Local;

@Local
public interface UserDao extends CrudDao<User, String> {
}
