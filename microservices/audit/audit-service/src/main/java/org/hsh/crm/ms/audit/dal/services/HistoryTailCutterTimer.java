package org.hsh.crm.ms.audit.dal.services;

import org.hsh.crm.ms.audit.bll.repository.AuditSettingsRepository;
import org.hsh.ms.common.bll.timer.BasePeriodicTimer;

import javax.annotation.Resource;
import javax.ejb.*;

@Singleton
@LocalBean
@DependsOn({"AppInitializer"})
@Startup
public class HistoryTailCutterTimer extends BasePeriodicTimer {
    @EJB
    private AuditSettingsRepository repoSettings;

    @Resource
    private TimerService sTimer;

    public HistoryTailCutterTimer() {
        super("history cutter timer");
    }

    @Override
    protected void taskProcess() {
        // ..
    }

    @Override
    protected TimerService getTimerService() {
        return sTimer;
    }
}
