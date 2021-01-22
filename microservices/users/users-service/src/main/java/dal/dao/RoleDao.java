package dal.dao;

import common.dal.dao.CrudDao;
import dal.entities.Role;

import javax.ejb.Local;

@Local
public interface RoleDao extends CrudDao<Role, String> {
}
