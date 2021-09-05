package org.hsh.crm.ms.audit.dal.repository;

import org.hsh.crm.ms.audit.bll.repository.UsersSettingsRepository;
import org.hsh.crm.ms.audit.bll.settings.UsersSettingsEnum;
import common.bll.repository.BaseSettingsRepository;
import org.hsh.crm.ms.audit.dal.dao.SettingDao;
import microservice.common.dal.entities.Setting;

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
