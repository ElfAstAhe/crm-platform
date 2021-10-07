package org.hsh.crm.ms.audit.dal.services;

import org.hsh.crm.ms.audit.bll.repository.AuditSettingsRepository;
import org.hsh.ms.common.bll.timer.BaseTimer;

import javax.annotation.Resource;
import javax.ejb.*;

@Singleton
@LocalBean
@DependsOn({"AppInitializer"})
@Startup
public class HistoryTailCutterTimer extends BaseTimer {
    @EJB
    private AuditSettingsRepository repoSettings;

    @Resource
    private TimerService sTimer;

    @Override
    protected TimerService getTimerService() {
        return sTimer;
    }

    @Override
    protected void defaultTask() {

    }
}
