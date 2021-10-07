package org.hsh.crm.ms.audit.dal.services;

import org.hsh.ms.common.bll.timer.BaseTimer;

import javax.annotation.Resource;
import javax.ejb.DependsOn;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.TimerService;

@Singleton
@LocalBean
@DependsOn({"AppInitializer"})
public class StrategySwitchTimer extends BaseTimer {
    private static long

    @Resource
    private TimerService sTimer;

    @Override
    protected TimerService getTimerService() {
        return sTimer;
    }

    @Override
    protected void defaultTask() {

    }

    @Override
    protected long getStartDelay() {
        return super.getStartDelay();
    }

    @Override
    protected long getPeriodicInterval() {
        return super.getPeriodicInterval();
    }
}
