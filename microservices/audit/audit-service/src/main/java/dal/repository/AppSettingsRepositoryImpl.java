package dal.repository;

import bll.repository.AppSettingsRepository;
import bll.settings.AppSettingsEnum;
import common.bll.repository.BaseSettingsRepository;
import dal.dao.SettingDao;
import dal.entities.Setting;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class AppSettingsRepositoryImpl
        extends BaseSettingsRepository<AppSettingsEnum>
        implements AppSettingsRepository
{
    @EJB
    private SettingDao daoSetting;

    @Override
    protected String getFromSource(AppSettingsEnum setting) {
        Setting entity = daoSetting.findByKey(setting.toString());
        if (entity == null)
            return null;
        return entity.getValue();
    }

    @Override
    protected void setToSource(AppSettingsEnum setting, String value) {
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
