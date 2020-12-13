package dal.migrations;

import common.dal.migration.BaseSqlMigration;
import common.dal.migration.SqlMigrationHelper;
import common.exceptions.DalException;
import dal.DalConstants;
import org.flywaydb.core.api.migration.Context;
import org.jooq.CreateSequenceFlagsStep;
import org.jooq.CreateTableColumnStep;
import org.jooq.DSLContext;
import org.jooq.conf.StatementType;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;

import java.time.OffsetDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

@SuppressWarnings("unused")
public class Migration20201009 extends BaseSqlMigration {
    private static final Logger logger = Logger.getLogger(Migration20201009.class.getName());
    private static final String VERSION = "1.0";
    private static final int CHECK_SUM = 0;
    private static final String DESCRIPTION = "Initial";

    private static final String TABLE_DATA_AUDIT = "data_audit";

    public Migration20201009() {
        super(VERSION, CHECK_SUM, DESCRIPTION);
    }

    @Override
    public void migrate(Context context) throws Exception {
        createTableSettings(context);

        // ToDo: elf: доработать с учётом шардирования
        createTableDataAudit(context);

        createSequenceObjects(context);
    }

    private void createSequenceObjects(Context context) throws DalException {
        logger.entering(this.getClass().getName(), "createSequenceObjects ..");
        try {
            DSLContext create = DSL.using(context.getConnection());
            // стройки
            create.settings().setStatementType(StatementType.STATIC_STATEMENT);
            // сиквенс
            String script;
            try (CreateSequenceFlagsStep csfs = create.createSequenceIfNotExists(DSL.sequence(DSL.name(DalConstants.SEQUENCE_OBJECTS), SQLDataType.BIGINT))) {
                script = csfs
                        .incrementBy(DSL.val(new Long(1)))
                        .minvalue(DSL.val(new Long(1)))
                        .startWith(DSL.val(new Long(1)))
                        .cache(DSL.val(new Long(1)))
                        .noCycle()
                        .getSQL();
                logger.info(String.format(SqlMigrationHelper.LogTemplate.SEQUENCE_SCRIPT, script));
                create.execute(script);
            }
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "createSequenceObjects error", ex);
            throw new DalException("createSequenceObjects error", ex);
        } finally {
            logger.exiting(this.getClass().getName(), "createSequenceObjects done");
        }
    }

    private void createTableDataAudit(Context context) throws DalException {
        logger.entering(this.getClass().getName(), "createTableDataAudit ..");
        try {
            DSLContext create = DSL.using(context.getConnection());
            // Конфигурируем
            create.settings().setStatementType(StatementType.STATIC_STATEMENT);
            // Генерируем таблицу
            String script;
            try (CreateTableColumnStep ctcs = create.createTableIfNotExists(DSL.name(TABLE_DATA_AUDIT))) {
                script = ctcs
                        .column(DSL.name(SqlMigrationHelper.Field.ID), SQLDataType.BIGINT.nullable(false))
                        .column(DSL.name("event_date"), SQLDataType.OFFSETDATETIME.nullable(false).defaultValue(OffsetDateTime.now()))
                        .column(DSL.name(SqlMigrationHelper.Field.SOURCE), SQLDataType.VARCHAR(100).nullable(true))
                        .column(DSL.name(SqlMigrationHelper.Field.REQUEST_ID), SQLDataType.VARCHAR(50).nullable(true))
                        .column(DSL.name(SqlMigrationHelper.Field.EVENT), SQLDataType.VARCHAR(50).nullable(true))
                        .column(DSL.name("class_name"), SQLDataType.VARCHAR(512).nullable(true))
                        .column(DSL.name("class_description"), SQLDataType.VARCHAR(512).nullable(true))
                        .column(DSL.name("object_id"), SQLDataType.VARCHAR(50).nullable(true))
                        .column(DSL.name("object_name"), SQLDataType.VARCHAR(100).nullable(true))
                        .column(DSL.name("values"), SqlMigrationHelper.serverSpecificDataTypeLongText(create.dialect()).nullable(true))
                        .column(DSL.name(SqlMigrationHelper.Field.USER), SQLDataType.VARCHAR(100).nullable(true))
                        .column(DSL.name("run_as_user"), SQLDataType.VARCHAR(100).nullable(true))
                        .constraints(
                                DSL.constraint(DSL.name(SqlMigrationHelper.buildPkConstraintName(TABLE_DATA_AUDIT)))
                                        .primaryKey(DSL.name(SqlMigrationHelper.Field.ID)))
                        .getSQL();
                logger.info(String.format(SqlMigrationHelper.LogTemplate.TABLE_SCRIPT, script));
                create.execute(script);
            }
            {
                // Коментарии
                script = create.commentOnTable(DSL.name(TABLE_DATA_AUDIT))
                        .is("data audit")
                        .getSQL();
                logger.info(String.format(SqlMigrationHelper.LogTemplate.COMMENT_SCRIPT, script));
                create.execute(script);
            }
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "createTableDataAudit error", ex);
            throw new DalException("createTableDataAudit error", ex);
        } finally {
            logger.exiting(this.getClass().getName(), "createTableDataAudit done");
        }
    }

    private void createTableSettings(Context context) throws DalException {
        logger.entering(this.getClass().getName(), "createTableSettings ..");
        try {
            DSLContext create = DSL.using(context.getConnection());
            // Конфигурируем
            create.settings().setStatementType(StatementType.STATIC_STATEMENT);
            // Генерируем таблицу
            String script;
            try (CreateTableColumnStep ctcs = create.createTableIfNotExists(DSL.name(SqlMigrationHelper.Table.SETTINGS))) {
                script = ctcs
                        .column(DSL.name(SqlMigrationHelper.Field.ID), SQLDataType.BIGINT.nullable(false))
                        .column(DSL.name(SqlMigrationHelper.Field.VERSION), SQLDataType.BIGINT.nullable(false))
                        .column(DSL.name(SqlMigrationHelper.Field.CODE), SQLDataType.VARCHAR(50).nullable(false))
                        .column(DSL.name(SqlMigrationHelper.Field.NAME), SQLDataType.VARCHAR(100).nullable(true))
                        .column(DSL.name(SqlMigrationHelper.Field.VALUE), SQLDataType.VARCHAR(1024).nullable(true))
                        .constraints(
                                DSL.constraint(DSL.name(SqlMigrationHelper.buildPkConstraintName(SqlMigrationHelper.Table.SETTINGS)))
                                        .primaryKey(DSL.name(SqlMigrationHelper.Field.ID)),
                                DSL.constraint(DSL.name(SqlMigrationHelper.buildUkConstraintName(SqlMigrationHelper.Table.SETTINGS)))
                                        .unique(DSL.name(SqlMigrationHelper.Field.CODE)))
                        .getSQL();
                logger.info(String.format(SqlMigrationHelper.LogTemplate.TABLE_SCRIPT, script));
                create.execute(script);
            }
            {
                // Коментарии
                script = create.commentOnTable(DSL.name(SqlMigrationHelper.Table.SETTINGS))
                        .is("Settings")
                        .getSQL();
                logger.info(String.format(SqlMigrationHelper.LogTemplate.COMMENT_SCRIPT, script));
                create.execute(script);
            }
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "createTableSettings error", ex);
            throw new DalException("createTableSettings error", ex);
        } finally {
            logger.exiting(this.getClass().getName(), "createTableSettings done");
        }
    }
}
