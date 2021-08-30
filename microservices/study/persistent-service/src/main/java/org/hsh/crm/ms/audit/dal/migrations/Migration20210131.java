package org.hsh.crm.ms.audit.dal.migrations;

import common.dal.migration.SqlMigrationHelper;
import common.dal.migration.base.BaseSqlMigration;
import org.jooq.CreateTableColumnStep;
import org.jooq.DSLContext;
import org.jooq.Query;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;

public class Migration20210131 extends BaseSqlMigration {
    private static final String VERSION = "1.0";
    private static final int CHECK_SUM = 0;
    private static final String DESCRIPTION = "Initial";

    private static final String TABLE_TEST_DATA = "test_data";

    public Migration20210131() {
        super(VERSION, CHECK_SUM, DESCRIPTION);
    }

    @Override
    protected void migrate(DSLContext dslContext) {
        SqlMigrationHelper.createDefaultSequenceObjects(dslContext);

        SqlMigrationHelper.createDefaultTableSettings(dslContext);

        SqlMigrationHelper.Ddl.createTable(dslContext, TABLE_TEST_DATA, this::buildTableTestData);
    }

    private Query buildTableTestData(CreateTableColumnStep ctcs) {
        return ctcs.column(DSL.name(SqlMigrationHelper.Field.ID), SQLDataType.BIGINT.nullable(false))
                .column(DSL.name("dummy"), SQLDataType.VARCHAR(50).nullable(false))
                .column(DSL.name("dummy_2"), SQLDataType.VARCHAR(200).nullable(true))
                .constraints(DSL.constraint(DSL.name(SqlMigrationHelper.Builder.buildPkConstraintName(TABLE_TEST_DATA))).primaryKey(DSL.name(SqlMigrationHelper.Field.ID)),
                        DSL.constraint(DSL.name(SqlMigrationHelper.Builder.buildUkConstraintName(TABLE_TEST_DATA))).unique(DSL.name("dummy")));
    }
}
