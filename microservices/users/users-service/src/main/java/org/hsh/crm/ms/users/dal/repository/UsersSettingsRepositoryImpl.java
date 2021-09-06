package org.hsh.crm.ms.users.dal.repository;

import org.hsh.crm.ms.common.dal.entities.Setting;
import org.hsh.crm.ms.users.bll.repository.UsersSettingsRepository;
import org.hsh.crm.ms.users.bll.settings.UsersSettingsEnum;
import org.hsh.crm.ms.users.dal.dao.SettingDao;
import org.hsh.ms.common.bll.repository.BaseSettingsRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class UsersSettingsRepositoryImpl
        extends BaseSettingsRepository<UsersSettingsEnum>
        implements UsersSettingsRepository
{
    @EJB
    private SettingDao daoSetting;

    @Override
    protected String getFromSource(UsersSettingsEnum setting) {
        Setting entity = daoSetting.findByKey(setting.toString());
        if (entity == null)
            return null;
        return entity.getValue();
    }

    @Override
    protected void setToSource(UsersSettingsEnum setting, String value) {
        Setting entity = daoSetting.findByKey(setting.toString());
        if (entity == null) {
            entity = new Setting(null,setting.toString(),null, value);
            daoSetting.create(entity);
            return;
        }
        entity.setValue(value);
        daoSetting.edit(entity);
    }
}
