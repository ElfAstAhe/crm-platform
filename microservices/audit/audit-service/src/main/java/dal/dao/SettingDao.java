package dal.dao;

import common.dal.dao.CrudDao;
import microservice.common.dal.entities.Setting;

import javax.ejb.Local;

@Local
public interface SettingDao extends CrudDao<Setting,String> {
}
