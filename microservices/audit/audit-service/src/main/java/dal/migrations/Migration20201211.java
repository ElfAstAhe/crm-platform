package dal.migrations;

import common.dal.migration.base.BaseSqlMigration;
import common.dal.migration.SqlMigrationHelper;
import org.jooq.AlterTableStep;
import org.jooq.CreateTableColumnStep;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;

import java.time.OffsetDateTime;
import java.util.logging.Logger;

@SuppressWarnings({"unused"})
public class Migration20201211 extends BaseSqlMigration {
    private final Logger logger = Logger.getLogger(Migration20201211.class.getName());
    private static final String VERSION = "1.1";
    private static final int CHECK_SUM = 1;
    private static final String DESCRIPTION = "Security audit table";

    private static final String TABLE_SECURITY_AUDIT = "security_audit";
    private static final String TABLE_DATA_AUDIT = "data_audit";

    public Migration20201211() {
        super(VERSION, CHECK_SUM, DESCRIPTION);
    }

    @Override
    protected void migrate(DSLContext context) {
        SqlMigrationHelper.Ddl
                .createTable(context, TABLE_SECURITY_AUDIT, this::buildTableSecurityAuditScript, "security audit");

        SqlMigrationHelper.Ddl
                .alterTable(context, TABLE_DATA_AUDIT, this::buildTableDataAuditScript);
    }

    private String buildTableSecurityAuditScript(CreateTableColumnStep ctcs) {
        return ctcs.column(DSL.name(SqlMigrationHelper.Field.ID), SQLDataType.BIGINT.nullable(false))
                .column(DSL.name("event_date"), SQLDataType.OFFSETDATETIME.nullable(false).defaultValue(OffsetDateTime.now()))
                .column(DSL.name(SqlMigrationHelper.Field.SOURCE), SQLDataType.VARCHAR(100).nullable(true))
                .column(DSL.name(SqlMigrationHelper.Field.REQUEST_ID), SQLDataType.VARCHAR(50).nullable(true))
                .column(DSL.name(SqlMigrationHelper.Field.EVENT), SQLDataType.VARCHAR(50).nullable(true))
                .column(DSL.name(SqlMigrationHelper.Field.USER_LOGIN), SQLDataType.VARCHAR(100).nullable(true))
                .column(DSL.name(SqlMigrationHelper.Field.STATUS), SQLDataType.VARCHAR(50).nullable(true))
                .constraints(
                        DSL.constraint(DSL.name(SqlMigrationHelper.buildPkConstraintName(TABLE_SECURITY_AUDIT)))
                                .primaryKey(DSL.name(SqlMigrationHelper.Field.ID)))
                .getSQL();
    }

    private String buildTableDataAuditScript(AlterTableStep ats) {
        return ats.add(DSL.name(SqlMigrationHelper.Field.STATUS), SQLDataType.VARCHAR(50).nullable(true))
                .getSQL();
    }
}
