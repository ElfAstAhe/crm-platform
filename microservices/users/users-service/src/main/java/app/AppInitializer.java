package app;

import common.app.BaseAppInitializer;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.sql.DataSource;

@Singleton
@Startup
public class AppInitializer extends BaseAppInitializer {
    @Resource(lookup = "jdbc/users")
    private DataSource dataSource;

    @Override
    protected DataSource getDataSource() {
        return dataSource;
    }
}
