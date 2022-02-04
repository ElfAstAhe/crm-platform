package org.hsh.crm.ms.study.swi.app;

import org.hsh.common.app.AppInitializer;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

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
