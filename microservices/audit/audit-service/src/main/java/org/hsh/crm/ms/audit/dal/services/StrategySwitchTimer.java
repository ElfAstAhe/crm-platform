package org.hsh.crm.ms.audit.dal.services;

import org.hsh.crm.ms.audit.bll.repository.AuditSettingsRepository;
import org.hsh.crm.ms.audit.bll.repository.DataAuditRepository;
import org.hsh.crm.ms.audit.bll.settings.AuditSettingsEnum;
import org.hsh.crm.ms.audit.dal.dao.AuditDaoStrategyKeyEnum;
import org.hsh.ms.common.bll.timer.BasePeriodicTimer;

import javax.annotation.Resource;
import javax.ejb.*;
import java.time.OffsetDateTime;

@Singleton
@LocalBean
@DependsOn({"AppInitializer"})
public class StrategySwitchTimer extends BasePeriodicTimer {
    @Resource
    private TimerService sTimer;

    @EJB
    private AuditSettingsRepository repoSettings;

    @EJB
    private DataAuditRepository repoDataAudit;

    public StrategySwitchTimer() {
        super("strategy switcher");
    }

    @Override
    protected TimerService getTimerService() {
        return sTimer;
    }

    @Override
    protected long getStartDelay() {
        return super.getStartDelay();
    }

    @Override
    protected long getPeriodicInterval() {
        return super.getPeriodicInterval();
    }

    @Override
    protected void taskProcess() {
        int maxDays = repoSettings.getIntegerValue(AuditSettingsEnum.MAX_DAYS_CURRENT);
        OffsetDateTime markerDate = OffsetDateTime.now().minusDays(maxDays);
        if(repoDataAudit.isEarlyExists(markerDate)) {
            AuditDaoStrategyKeyEnum current = AuditDaoStrategyKeyEnum.valueOf(repoSettings.getStringValue(AuditSettingsEnum.DATA_AUDIT_STRATEGY));
            repoSettings.setStringValue(AuditSettingsEnum.DATA_AUDIT_STRATEGY, getNextStrategy(current).toString());
        }
    }

    private AuditDaoStrategyKeyEnum getNextStrategy(AuditDaoStrategyKeyEnum current) {
        return current.nextKey();
    }
}
