package org.hsh.crm.ms.audit.app;

import common.app.BaseAppInitializer;
import org.hsh.crm.ms.audit.dal.DalConstants;

import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.sql.DataSource;

@Singleton
@Startup
public class AppInitializer extends BaseAppInitializer {
    @Resource(lookup = DalConstants.DATA_SOURCE)
    private DataSource dataSource;

    @Override
    protected DataSource getDataSource() {
        return dataSource;
    }
}
