package app;

import common.app.AppInitializer;
import common.dal.migration.DatabaseMigrator;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.sql.DataSource;

@Singleton
@Startup
public class Initializer implements AppInitializer {
    @Resource(lookup = "jdbc/audit")
    private DataSource dataSource;

    private boolean ready = false;

    @PostConstruct
    public void postConstruct() throws RuntimeException {
        DatabaseMigrator.up(dataSource);
        ready = true;
    }

    @Override
    public boolean isReady() {
        return ready;
    }
}
