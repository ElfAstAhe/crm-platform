package org.hsh.crm.ms.users.bll.repository;

import org.hsh.common.bll.repository.SettingsRepository;
import org.hsh.crm.ms.users.bll.settings.UsersSettingsEnum;

import javax.ejb.Local;

@Local
public interface UsersSettingsRepository extends SettingsRepository<UsersSettingsEnum> {
}
