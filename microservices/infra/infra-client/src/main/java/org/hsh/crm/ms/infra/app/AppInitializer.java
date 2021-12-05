package org.hsh.crm.ms.infra.app;

import org.hsh.crm.ms.infra.dal.DalConstants;
import org.hsh.ms.common.app.BaseAppInitializer;

import javax.annotation.Resource;
import javax.sql.DataSource;

public class AppInitializer extends BaseAppInitializer {
    @Resource(lookup = DalConstants.DATA_SOURCE)
    private DataSource dataSource;

    @Override
    protected DataSource getDataSource() {
        return dataSource;
    }
}
