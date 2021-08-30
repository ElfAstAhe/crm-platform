package org.hsh.crm.ms.audit.bll.repository;

import org.hsh.crm.ms.audit.bll.settings.UsersSettingsEnum;
import common.bll.repository.SettingsRepository;

import javax.ejb.Local;

@Local
public interface UsersSettingsRepository extends SettingsRepository<UsersSettingsEnum> {
}
