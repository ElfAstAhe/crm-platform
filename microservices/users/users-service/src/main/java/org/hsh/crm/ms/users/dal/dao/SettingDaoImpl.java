package org.hsh.crm.ms.users.dal.dao;

import org.hsh.crm.ms.common.dal.entities.Setting;

import javax.ejb.Stateless;

@Stateless
public class SettingDaoImpl extends BaseUsersDao<Setting, String> implements SettingDao {
    public SettingDaoImpl() {
        super(Setting.class);
    }
}
