package org.hsh.crm.ms.audit.dal.migrations;

import org.hsh.common.dal.migration.SqlMigrationHelper;
import org.hsh.common.dal.migration.base.BaseSqlMigration;
import org.jooq.CreateTableColumnStep;
import org.jooq.DSLContext;
import org.jooq.Query;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;

import java.time.OffsetDateTime;
import java.util.Objects;

public class Migration20210916 extends BaseSqlMigration {
    private static final String VERSION = "1.2";
    private static final int CHECK_SUM = 2;
    private static final String DESCRIPTION = "history tables data and security audit";

    private static final String TABLE_SECURITY_AUDIT_HISTORY = "security_audit_history";
    private static final String TABLE_DATA_AUDIT_HISTORY = "data_audit_history";

    public Migration20210916() {
        super(VERSION, CHECK_SUM, DESCRIPTION);
    }

    @Override
    protected void migrate(DSLContext dslContext) {
        SqlMigrationHelper.Ddl.createTable(dslContext, TABLE_DATA_AUDIT_HISTORY, "data audit history", this::buildTableDataAuditHistory);
        SqlMigrationHelper.Ddl.createTable(dslContext, TABLE_SECURITY_AUDIT_HISTORY, "security audit history", this::buildTableSecurityAuditHistory);
    }

    private Query buildTableDataAuditHistory(CreateTableColumnStep ctcs) {
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
                   .column(DSL.name("run_as_user"), SQLDataType.VARCHAR(100).nullable(true))
                   .constraints(DSL.constraint(DSL.name(SqlMigrationHelper.Builder.buildPkConstraintName(TABLE_DATA_AUDIT_HISTORY)))
                                   .primaryKey(DSL.name(SqlMigrationHelper.Field.ID)));
    }

    private Query buildTableSecurityAuditHistory(CreateTableColumnStep ctcs) {
        return ctcs.column(DSL.name(SqlMigrationHelper.Field.ID), SQLDataType.BIGINT.nullable(false))
                .column(DSL.name("event_date"), SQLDataType.OFFSETDATETIME.nullable(false).defaultValue(OffsetDateTime.now()))
                .column(DSL.name(SqlMigrationHelper.Field.STATUS), SQLDataType.VARCHAR(50).nullable(true))
                .column(DSL.name(SqlMigrationHelper.Field.SOURCE), SQLDataType.VARCHAR(100).nullable(true))
                .column(DSL.name(SqlMigrationHelper.Field.REQUEST_ID), SQLDataType.VARCHAR(50).nullable(true))
                .column(DSL.name(SqlMigrationHelper.Field.EVENT), SQLDataType.VARCHAR(50).nullable(true))
                .column(DSL.name(SqlMigrationHelper.Field.USER_LOGIN), SQLDataType.VARCHAR(100).nullable(true))
                .constraints(DSL.constraint(DSL.name(SqlMigrationHelper.Builder.buildPkConstraintName(TABLE_SECURITY_AUDIT_HISTORY)))
                                .primaryKey(DSL.name(SqlMigrationHelper.Field.ID)));
    }
}
