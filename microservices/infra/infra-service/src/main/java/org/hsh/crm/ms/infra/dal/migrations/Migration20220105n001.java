package org.hsh.crm.ms.infra.dal.migrations;

import org.hsh.common.dal.migration.SqlMigrationHelper;
import org.hsh.common.dal.migration.base.BaseSqlMigration;
import org.jooq.DSLContext;

public class Migration20220105n001 extends BaseSqlMigration {
    public Migration20220105n001() {
        super("1.0", 0, "Default objects");
    }

    @Override
    protected void migrate(DSLContext dslContext) {
        SqlMigrationHelper.createDefaultSequenceObjects(dslContext);
        SqlMigrationHelper.createDefaultTableSettings(dslContext);
    }
}
