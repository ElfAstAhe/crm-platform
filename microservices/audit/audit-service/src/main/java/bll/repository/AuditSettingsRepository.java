package bll.repository;

import bll.settings.AuditSettingsEnum;
import org.hsh.common.bll.repository.SettingsRepository;

import javax.ejb.Local;

@Local
public interface AuditSettingsRepository extends SettingsRepository<AuditSettingsEnum> {
}
