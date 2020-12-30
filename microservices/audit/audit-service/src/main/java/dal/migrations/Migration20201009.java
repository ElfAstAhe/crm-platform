package dal.migrations;

import common.dal.migration.base.BaseSqlMigration;
import common.dal.migration.SqlMigrationHelper;
import dal.DalConstants;
import org.jooq.CreateSequenceFlagsStep;
import org.jooq.CreateTableColumnStep;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;

import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.logging.Logger;

@SuppressWarnings({"unused", "DuplicatedCode"})
public class Migration20201009 extends BaseSqlMigration {
    private final Logger logger = Logger.getLogger(Migration20201009.class.getName());
    private static final String VERSION = "1.0";
    private static final int CHECK_SUM = 0;
    private static final String DESCRIPTION = "Initial";

    private static final String TABLE_DATA_AUDIT = "data_audit";

    public Migration20201009() {
        super(VERSION, CHECK_SUM, DESCRIPTION);
    }

    @Override
    protected void migrate(DSLContext context) {
        SqlMigrationHelper.Ddl
                .createTable(context, SqlMigrationHelper.Table.SETTINGS, this::buildTableSettingsScript, "settings");

        // ToDo: elf: доработать с учётом шардирования
        SqlMigrationHelper.Ddl
                .createTable(context, TABLE_DATA_AUDIT, this::buildTableDataAuditScript, "data audit");

        SqlMigrationHelper.Ddl
                .createSequence(context, DalConstants.SEQUENCE_OBJECTS, this::buildSequenceObjectsScript);
    }

    private String buildSequenceObjectsScript(CreateSequenceFlagsStep csfs) {
        return csfs.incrementBy(DSL.val(new Long(1)))
                .minvalue(DSL.val(new Long(1)))
                .startWith(DSL.val(new Long(1)))
                .cache(DSL.val(new Long(1)))
                .noCycle()
                .getSQL();
    }

    private String buildTableDataAuditScript(CreateTableColumnStep ctcs) {
        return ctcs.column(DSL.name(SqlMigrationHelper.Field.ID), SQLDataType.BIGINT.nullable(false))
                .column(DSL.name("event_date"), SQLDataType.OFFSETDATETIME.nullable(false).defaultValue(OffsetDateTime.now()))
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
                .constraints(DSL.constraint(DSL.name(SqlMigrationHelper.buildPkConstraintName(TABLE_DATA_AUDIT)))
                        .primaryKey(DSL.name(SqlMigrationHelper.Field.ID)))
                .getSQL();
    }

    private String buildTableSettingsScript(CreateTableColumnStep ctcs) {
        return ctcs.column(DSL.name(SqlMigrationHelper.Field.ID), SQLDataType.BIGINT.nullable(false))
                .column(DSL.name(SqlMigrationHelper.Field.VERSION), SQLDataType.BIGINT.nullable(false))
                .column(DSL.name(SqlMigrationHelper.Field.CODE), SQLDataType.VARCHAR(50).nullable(false))
                .column(DSL.name(SqlMigrationHelper.Field.NAME), SQLDataType.VARCHAR(100).nullable(true))
                .column(DSL.name(SqlMigrationHelper.Field.VALUE), SQLDataType.VARCHAR(1024).nullable(true))
                .constraints(DSL.constraint(DSL.name(SqlMigrationHelper.buildPkConstraintName(SqlMigrationHelper.Table.SETTINGS)))
                                .primaryKey(DSL.name(SqlMigrationHelper.Field.ID)),
                        DSL.constraint(DSL.name(SqlMigrationHelper.buildUkConstraintName(SqlMigrationHelper.Table.SETTINGS)))
                                .unique(DSL.name(SqlMigrationHelper.Field.CODE)))
                .getSQL();
    }
}
