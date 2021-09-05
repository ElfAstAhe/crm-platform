package org.hsh.crm.ms.audit.dal.migrations;

import org.hsh.common.dal.migration.SqlMigrationHelper;
import org.hsh.common.dal.migration.base.BaseSqlMigration;
import org.jooq.AlterTableStep;
import org.jooq.CreateTableColumnStep;
import org.jooq.DSLContext;
import org.jooq.Query;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;

import java.time.OffsetDateTime;

public class Migration20201211 extends BaseSqlMigration {
    private static final String VERSION = "1.1";
    private static final int CHECK_SUM = 1;
    private static final String DESCRIPTION = "Security audit table";

    private static final String TABLE_SECURITY_AUDIT = "security_audit";
    private static final String TABLE_DATA_AUDIT = "data_audit";

    public Migration20201211() {
        super(VERSION, CHECK_SUM, DESCRIPTION);
    }

    @Override
    protected void migrate(DSLContext dslContext) {
        SqlMigrationHelper.Ddl
                .createTable(dslContext, TABLE_SECURITY_AUDIT, "security audit", this::buildTableSecurityAudit);

        SqlMigrationHelper.Ddl
                .alterTable(dslContext, TABLE_DATA_AUDIT, this::alterTableDataAudit);
    }

    private Query buildTableSecurityAudit(CreateTableColumnStep ctcs) {
        return ctcs.column(DSL.name(SqlMigrationHelper.Field.ID), SQLDataType.BIGINT.nullable(false))
                   .column(DSL.name("event_date"), SQLDataType.OFFSETDATETIME.nullable(false).defaultValue(OffsetDateTime.now()))
                   .column(DSL.name(SqlMigrationHelper.Field.SOURCE), SQLDataType.VARCHAR(100).nullable(true))
                   .column(DSL.name(SqlMigrationHelper.Field.REQUEST_ID), SQLDataType.VARCHAR(50).nullable(true))
                   .column(DSL.name(SqlMigrationHelper.Field.EVENT), SQLDataType.VARCHAR(50).nullable(true))
                   .column(DSL.name(SqlMigrationHelper.Field.USER_LOGIN), SQLDataType.VARCHAR(100).nullable(true))
                   .column(DSL.name(SqlMigrationHelper.Field.STATUS), SQLDataType.VARCHAR(50).nullable(true))
                   .constraints(
                           DSL.constraint(DSL.name(SqlMigrationHelper.Builder.buildPkConstraintName(TABLE_SECURITY_AUDIT)))
                              .primaryKey(DSL.name(SqlMigrationHelper.Field.ID)));
    }

    private Query alterTableDataAudit(AlterTableStep ats) {
        return ats.add(DSL.name(SqlMigrationHelper.Field.STATUS), SQLDataType.VARCHAR(50).nullable(true));
    }
}
