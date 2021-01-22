package app;

import common.app.BaseAppInitializer;

import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.sql.DataSource;

@SuppressWarnings("unused")
@Singleton
@Startup
public class AppInitializer extends BaseAppInitializer {
    @Resource(lookup = "jdbc/audit")
    private DataSource dataSource;

    @Override
    protected DataSource getDataSource() {
        return dataSource;
    }
}
