package org.hsh.crm.ms.infra.dal.migrations;

import org.hsh.common.dal.migration.SqlMigrationHelper;
import org.hsh.common.dal.migration.base.BaseSqlMigration;
import org.jooq.CreateTableColumnStep;
import org.jooq.DSLContext;
import org.jooq.Query;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;

public class Migration20220105n002 extends BaseSqlMigration {
    private static final String TABLE_RESOURCES = "resources";

    public Migration20220105n002() {
        super("1.1", 1, "Domain objects");
    }

    @Override
    protected void migrate(DSLContext dslContext) {
        SqlMigrationHelper.Ddl.createTable(dslContext, TABLE_RESOURCES, "infra resources", this::createTableResources);
    }

    private Query createTableResources(CreateTableColumnStep ctcs) {
        return ctcs.column(DSL.name(SqlMigrationHelper.Field.CODE), SQLDataType.VARCHAR(50).nullable(false))
                .column(DSL.name(SqlMigration))
    }
}
