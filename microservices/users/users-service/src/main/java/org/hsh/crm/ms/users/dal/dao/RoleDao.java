package org.hsh.crm.ms.users.dal.dao;

import org.hsh.common.dal.dao.CrudDao;
import org.hsh.crm.ms.users.dal.entities.Role;

import javax.ejb.Local;

@Local
public interface RoleDao extends CrudDao<Role, Long> {
}
