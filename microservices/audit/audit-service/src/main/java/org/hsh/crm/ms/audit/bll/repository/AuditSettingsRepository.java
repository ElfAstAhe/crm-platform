package org.hsh.crm.ms.audit.bll.repository;

import org.hsh.crm.ms.audit.bll.settings.AuditSettingsEnum;
import org.hsh.common.bll.repository.SettingsRepository;

import javax.ejb.Local;

@Local
public interface AuditSettingsRepository extends SettingsRepository<AuditSettingsEnum> {
}
