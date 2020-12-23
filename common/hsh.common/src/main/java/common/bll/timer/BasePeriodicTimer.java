package common.bll.timer;

import common.exceptions.base.RsException;

import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
    base periodic timer service,
    all ancestors must be EJB
*/
@SuppressWarnings("unused")
public abstract class BasePeriodicTimer {
    private final static Logger logger = Logger.getLogger(BasePeriodicTimer.class.getName());
    protected static final String timerName = "periodicTimer";
    private Timer singleActionTimer;
    private AtomicBoolean finished;

    public void postConstruct() {
        finished = new AtomicBoolean(false);
        singleActionTimer = buildTimer(getTimerDurationSetting().longValue());
    }

    public void preDestroy() {
        finished.set(true);
        destroyTimer();
    }

    @Timeout
    public void timerEvent(Timer eventTimer) {
        if (!finished.get()) {
            // Подгружаем настройку таймера
            Future<Integer> timeMills = getTimerDurationSettingAsync();
            long defaultTimeMills = getDefaultTimerDurationSetting();
            try {
                fireTimerEvent();
            } catch (ExecutionException | InterruptedException | RsException ex) {
                logger.log(Level.SEVERE, "Error timer event trigger", ex);
            } finally {
                // Пересоздаём timer
                try {
                    singleActionTimer = buildTimer(timeMills.get().longValue());
                } catch (InterruptedException | ExecutionException ex) {
                    logger.log(Level.SEVERE, "Error create timer, using default setting", ex);
                    singleActionTimer = buildTimer(defaultTimeMills);
                }
            }
        }
    }

    protected Timer buildTimer(long timeMillis) {
        return singleActionTimer = getTimerService().createSingleActionTimer(
                timeMillis,
                new TimerConfig(timerName, false));
    }

    protected void destroyTimer() {
        if (singleActionTimer != null)
            singleActionTimer.cancel();
    }

    protected AtomicBoolean isFinished(){
        return finished;
    }

    protected abstract void fireTimerEvent() throws ExecutionException,InterruptedException, RsException;
    protected abstract Future<Integer> getTimerDurationSettingAsync();
    protected abstract Integer getTimerDurationSetting();
    protected abstract long getDefaultTimerDurationSetting();
    protected abstract TimerService getTimerService();
}
