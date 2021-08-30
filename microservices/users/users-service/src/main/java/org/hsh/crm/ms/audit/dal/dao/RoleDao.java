package org.hsh.crm.ms.audit.dal.dao;

import common.dal.dao.CrudDao;
import org.hsh.crm.ms.audit.dal.entities.Role;

import javax.ejb.Local;

@Local
public interface RoleDao extends CrudDao<Role, String> {
}