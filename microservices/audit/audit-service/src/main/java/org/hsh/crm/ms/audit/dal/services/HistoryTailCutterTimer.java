package org.hsh.crm.ms.audit.dal.services;

import org.hsh.crm.ms.audit.bll.repository.AuditSettingsRepository;

import javax.ejb.*;

@Singleton
@LocalBean
@DependsOn({"AppInitializer"})
@Startup
public class HistoryTailCutterTimer {
    @EJB
    private AuditSettingsRepository repoSettings;

    
}
