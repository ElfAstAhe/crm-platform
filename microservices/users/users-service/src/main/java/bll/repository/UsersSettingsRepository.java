package bll.repository;

import bll.settings.UsersSettingsEnum;
import common.bll.repository.SettingsRepository;

import javax.ejb.Local;

@Local
public interface UsersSettingsRepository extends SettingsRepository<UsersSettingsEnum> {
}
