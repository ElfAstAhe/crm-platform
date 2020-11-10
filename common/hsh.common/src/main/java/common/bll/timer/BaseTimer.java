package common.bll.timer;

import org.apache.commons.lang3.time.DateUtils;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * base periodic or scheduled timer service
 */
public abstract class BaseTimer implements TimedObject {
    protected static final long WAIT_TIME_MILLIS = DateUtils.MILLIS_PER_SECOND;
    protected static final long DEFAULT_START_DELAY_MILLIS = 5L * DateUtils.MILLIS_PER_SECOND;
    protected static final long DEFAULT_PERIODIC_INTERVAL_MILLIS = 5 * DateUtils.MILLIS_PER_MINUTE;

    private transient final Logger logger;
    private final AtomicBoolean timerStarted = new AtomicBoolean(false);
    private final AtomicBoolean eventFired = new AtomicBoolean(false);
    private final AtomicBoolean taskExecuting = new AtomicBoolean(false);
    private transient Timer singleActionTimer;

    public BaseTimer() {
        logger = Logger.getLogger(this.getClass().getName());
    }

    @PostConstruct
    public void postConstruct() {
        autoStart();
    }

    @PreDestroy
    public void preDestroy() {
        stop();
    }

    @Override
    public void ejbTimeout(Timer timer) {
        if (isEventFired())
            return;
        try {
            if (timer == null) {
                logger.info("nullable timer, ignoring");
                return;
            }

            if (singleActionTimer == null) {
                logger.info("own timer is nullable, ignoring");
                timer.cancel();
                return;
            }

            if (!timer.equals(singleActionTimer)) {
                logger.info("own timer and param timer not equals, ignoring");
                timer.cancel();
                return;
            }

            eventFired.set(true);
            cancelTimer();

            processEvent();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "error fire timer event", ex);
        } finally {
            eventFired.set(false);
            singleActionTimer = createNextTimer(getTimerService());
            logger.info("next timer iteration started");
        }
    }

    public boolean isTimerStarted() {
        return timerStarted.get();
    }

    public boolean isEventFired() {
        return eventFired.get();
    }

    public boolean isTaskExecuting() {
        return taskExecuting.get();
    }

    public void start() {
        if(timerStarted.get()) {
            logger.info("Timer already started, ignore start..");
            return;
        }
        timerStarted.set(true);

        cancelTimer();
        singleActionTimer = createStartTimer(getTimerService());

        logger.info("Timer started");
    }

    public void stop() {
        cancelTimer();

        timerStarted.set(false);

        waitForStop();

        cancelTimer();
    }

    /**
     * can be override
     */
    protected void autoStart() {
        if (this.getClass().isAnnotationPresent(Startup.class)) {
            logger.info("Autorun timer, cause @Startup annotation detected");
            start();
        }
    }

    /**
     * can be override
     */
    protected void processEvent() {
        executeTask(this::defaultTask);
    }

    /**
     * can be override
     * @param task any method without params
     */
    protected void executeTask(Runnable task) {
        if (isTaskExecuting())
            return;
        try {
            taskExecuting.set(true);
            task.run();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Error executing task", ex);
        } finally {
            taskExecuting.set(false);
        }
    }

    /**
     * can be override
     */
    @SuppressWarnings("BusyWait")
    protected void waitForStop() {
        while (isTimerStarted() || isEventFired() || isTaskExecuting())
            try {
                Thread.sleep(WAIT_TIME_MILLIS);
            } catch (Exception ex) {
                // nothing
            }
    }

    protected abstract TimerService getTimerService();
    protected abstract void defaultTask();

    /**
     * can be override
     * @return timer config
     */
    protected TimerConfig getTimerConfig() {
        // default
        TimerConfig config = new TimerConfig();
        config.setPersistent(false);
        return config;
    }

    protected Timer createStartTimer(TimerService timerService) {
        return timerService.createSingleActionTimer(getStartDelay(), getTimerConfig());
    }

    protected Timer createNextTimer(TimerService timerService) {
        return timerService.createSingleActionTimer(getPeriodicInterval(), getTimerConfig());
    }

    protected long getStartDelay() {
        return DEFAULT_START_DELAY_MILLIS;
    }

    protected long getPeriodicInterval() {
        return DEFAULT_PERIODIC_INTERVAL_MILLIS;
    }

    private void cancelTimer() {
        if (singleActionTimer != null)
            try {
                singleActionTimer.cancel();
            } catch (NoSuchObjectLocalException ex) {
                // nothing
            }
        singleActionTimer = null;
    }
}
