package org.hsh.crm.ms.study.nps.app;

import org.hsh.common.app.AppInitializer;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@SuppressWarnings("unused")
@Singleton
@Startup
public class Initializer implements AppInitializer {
    private boolean ready = false;

    @PostConstruct
    public void postConstruct() {
        ready = true;
    }

    public boolean isReady() {
        return ready;
    }
}
