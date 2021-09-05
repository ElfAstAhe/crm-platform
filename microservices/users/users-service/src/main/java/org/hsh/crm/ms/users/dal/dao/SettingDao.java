package org.hsh.crm.ms.users.dal.dao;


import org.hsh.common.dal.dao.CrudDao;
import org.hsh.crm.ms.common.dal.entities.Setting;

import javax.ejb.Local;

@Local
public interface SettingDao extends CrudDao<Setting, String> {
}
