package org.hsh.crm.ms.audit.dal.repository;

import org.hsh.crm.ms.audit.bll.repository.AuditSettingsRepository;
import org.hsh.crm.ms.audit.bll.settings.AuditSettingsEnum;
import org.hsh.crm.ms.audit.dal.dao.SettingDao;
import org.hsh.crm.ms.common.dal.entities.Setting;
import org.hsh.ms.common.bll.repository.BaseSettingsRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class AuditSettingsRepositoryImpl extends BaseSettingsRepository<AuditSettingsEnum> implements AuditSettingsRepository {
    @Inject
    private SettingDao daoSetting;

    @Override
    protected String getFromSource(AuditSettingsEnum setting) {
        Setting entity = daoSetting.findByKey(setting.toString());
        if (entity == null)
            return null;
        return entity.getValue();
    }

    @Override
    protected void setToSource(AuditSettingsEnum setting, String value) {
        Setting entity = daoSetting.findByKey(setting.toString());
        if (entity == null) {
            entity = new Setting(null, setting.toString(),null, value);
            daoSetting.create(entity);
            return;
        }
        entity.setValue(value);
        daoSetting.edit(entity);
    }
}
