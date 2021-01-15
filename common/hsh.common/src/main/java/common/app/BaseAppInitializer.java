package common.app;

import common.dal.migration.DatabaseMigrator;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

public abstract class BaseAppInitializer implements AppInitializer{
    private boolean ready = false;

    @PostConstruct
    public void postConstruct() {
        DatabaseMigrator.up(getDataSource());
        ready = true;
    }

    @Override
    public boolean isReady() {
        return ready;
    }

    protected abstract DataSource getDataSource();
}
