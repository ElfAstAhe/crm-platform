package org.hsh.crm.ms.audit.dal.repository;

import org.hsh.crm.ms.audit.bll.repository.AuditSettingsRepository;
import org.hsh.crm.ms.audit.bll.settings.AuditSettingsEnum;
import common.bll.repository.BaseSettingsRepository;
import org.hsh.crm.ms.audit.dal.dao.SettingDao;
import microservice.common.dal.entities.Setting;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class AuditSettingsRepositoryImpl
        extends BaseSettingsRepository<AuditSettingsEnum>
        implements AuditSettingsRepository
{
    @EJB
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
            entity = new Setting(null,setting.toString(),null, value);
            daoSetting.create(entity);
            return;
        }
        entity.setValue(value);
        daoSetting.edit(entity);
    }
}