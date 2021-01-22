package dal.dao;

import common.dal.dao.CrudDao;
import dal.entities.User;

import javax.ejb.Local;

@Local
public interface UserDao extends CrudDao<User, String> {
}
