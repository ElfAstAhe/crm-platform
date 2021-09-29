package org.hsh.crm.ms.audit.dal.migrations;

import org.hsh.common.dal.migration.SqlMigrationHelper;
import org.hsh.common.dal.migration.base.BaseSqlMigration;
import org.jooq.DSLContext;

public class Migration20201009 extends BaseSqlMigration {
    private static final String VERSION = "1.0";
    private static final int CHECK_SUM = 0;
    private static final String DESCRIPTION = "Initial";

    public Migration20201009() {
        super(VERSION, CHECK_SUM, DESCRIPTION);
    }

    @Override
    protected void migrate(DSLContext dslContext) {
        SqlMigrationHelper.createDefaultSequenceObjects(dslContext);

        SqlMigrationHelper.createDefaultTableSettings(dslContext);
    }
}
