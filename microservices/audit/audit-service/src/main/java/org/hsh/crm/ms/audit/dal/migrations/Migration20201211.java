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
import java.util.Objects;
import java.util.function.Function;

public class Migration20201211 extends BaseSqlMigration {
    private static final String VERSION = "1.1";
    private static final int CHECK_SUM = 1;
    private static final String DESCRIPTION = "data and security audit tables";

    private static final String TABLE_DATA_AUDIT_1 = "data_audit_1";
    private static final String TABLE_DATA_AUDIT_2 = "data_audit_2";
    private static final String TABLE_SECURITY_AUDIT_1 = "security_audit_1";
    private static final String TABLE_SECURITY_AUDIT_2 = "security_audit_1";

    public Migration20201211() {
        super(VERSION, CHECK_SUM, DESCRIPTION);
    }

    @Override
    protected void migrate(DSLContext dslContext) {
        SqlMigrationHelper.Ddl.createTable(dslContext,
                TABLE_DATA_AUDIT_1,
                ctcs -> buildTableDataAudit(ctcs).constraints(DSL.constraint(DSL.name(SqlMigrationHelper.Builder.buildPkConstraintName(TABLE_DATA_AUDIT_1)))
                        .primaryKey(DSL.name(SqlMigrationHelper.Field.ID))));
        SqlMigrationHelper.Ddl.createTable(dslContext,
                TABLE_DATA_AUDIT_2,
                ctcs -> buildTableDataAudit(ctcs).constraints(DSL.constraint(DSL.name(SqlMigrationHelper.Builder.buildPkConstraintName(TABLE_DATA_AUDIT_2)))
                        .primaryKey(DSL.name(SqlMigrationHelper.Field.ID))));
        SqlMigrationHelper.Ddl.createTable(dslContext,
                TABLE_SECURITY_AUDIT_1,
                ctcs -> buildTableSecurityAudit(ctcs).constraints(DSL.constraint(DSL.name(SqlMigrationHelper.Builder.buildPkConstraintName(TABLE_SECURITY_AUDIT_1)))
                        .primaryKey(DSL.name(SqlMigrationHelper.Field.ID))));
        SqlMigrationHelper.Ddl.createTable(dslContext,
                TABLE_SECURITY_AUDIT_2,
                ctcs -> buildTableSecurityAudit(ctcs).constraints(DSL.constraint(DSL.name(SqlMigrationHelper.Builder.buildPkConstraintName(TABLE_SECURITY_AUDIT_2)))
                        .primaryKey(DSL.name(SqlMigrationHelper.Field.ID))));
    }

    private CreateTableColumnStep buildTableDataAudit(CreateTableColumnStep ctcs) {
        return ctcs.column(DSL.name(SqlMigrationHelper.Field.ID), SQLDataType.BIGINT.nullable(false))
                   .column(DSL.name("event_date"), SQLDataType.OFFSETDATETIME.nullable(false).defaultValue(OffsetDateTime.now()))
                   .column(DSL.name(SqlMigrationHelper.Field.STATUS), SQLDataType.VARCHAR(50).nullable(true))
                   .column(DSL.name(SqlMigrationHelper.Field.SOURCE), SQLDataType.VARCHAR(100).nullable(true))
                   .column(DSL.name(SqlMigrationHelper.Field.REQUEST_ID), SQLDataType.VARCHAR(50).nullable(true))
                   .column(DSL.name(SqlMigrationHelper.Field.EVENT), SQLDataType.VARCHAR(50).nullable(true))
                   .column(DSL.name("class_name"), SQLDataType.VARCHAR(512).nullable(true))
                   .column(DSL.name("class_description"), SQLDataType.VARCHAR(512).nullable(true))
                   .column(DSL.name("object_id"), SQLDataType.VARCHAR(50).nullable(true))
                   .column(DSL.name("object_name"), SQLDataType.VARCHAR(100).nullable(true))
                   .column(DSL.name("values"), SqlMigrationHelper.serverSpecificDataTypeLongText(Objects.requireNonNull(ctcs.configuration()).dialect()).nullable(true))
                   .column(DSL.name(SqlMigrationHelper.Field.USER_LOGIN), SQLDataType.VARCHAR(100).nullable(true))
                   .column(DSL.name("run_as_user"), SQLDataType.VARCHAR(100).nullable(true));
    }

    private CreateTableColumnStep buildTableSecurityAudit(CreateTableColumnStep ctcs) {
        return ctcs.column(DSL.name(SqlMigrationHelper.Field.ID), SQLDataType.BIGINT.nullable(false))
                   .column(DSL.name("event_date"), SQLDataType.OFFSETDATETIME.nullable(false).defaultValue(OffsetDateTime.now()))
                   .column(DSL.name(SqlMigrationHelper.Field.SOURCE), SQLDataType.VARCHAR(100).nullable(true))
                   .column(DSL.name(SqlMigrationHelper.Field.REQUEST_ID), SQLDataType.VARCHAR(50).nullable(true))
                   .column(DSL.name(SqlMigrationHelper.Field.EVENT), SQLDataType.VARCHAR(50).nullable(true))
                   .column(DSL.name(SqlMigrationHelper.Field.USER_LOGIN), SQLDataType.VARCHAR(100).nullable(true))
                   .column(DSL.name(SqlMigrationHelper.Field.STATUS), SQLDataType.VARCHAR(50).nullable(true));
    }
}
