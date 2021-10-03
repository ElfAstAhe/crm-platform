package org.hsh.crm.ms.study.ps.app;

import org.hsh.crm.ms.study.ps.dal.DalConstants;
import org.hsh.ms.common.app.BaseAppInitializer;

import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.sql.DataSource;

@Singleton
@Startup
@TransactionManagement(TransactionManagementType.BEAN)
public class AppInitializer extends BaseAppInitializer {
    @Resource(lookup = DalConstants.DATA_SOURCE)
    private DataSource dataSource;

    @Override
    protected DataSource getDataSource() {
        return dataSource;
    }
}