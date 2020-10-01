package bll.repository;

import bll.settings.AppSettingsEnum;
import common.bll.repository.SettingsRepository;

import javax.ejb.Local;

@Local
public interface AppSettingsRepository extends SettingsRepository<AppSettingsEnum> {
}
