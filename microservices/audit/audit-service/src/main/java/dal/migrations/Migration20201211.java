package dal.migrations;

import common.dal.migration.BaseSqlMigration;
import common.dal.migration.SqlMigrationHelper;
import common.exceptions.base.DalException;
import org.flywaydb.core.api.migration.Context;
import org.jooq.CreateTableColumnStep;
import org.jooq.DSLContext;
import org.jooq.conf.StatementType;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;

import java.time.OffsetDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Migration20201211 extends BaseSqlMigration {
    private static final Logger logger = Logger.getLogger(Migration20201211.class.getName());
    private static final String VERSION = "1.1";
    private static final int CHECK_SUM = 1;
    private static final String DESCRIPTION = "Security audit table";

    private static final String TABLE_SECURITY_AUDIT = "security_audit";
    private static final String TABLE_DATA_AUDIT = "data_audit";

    public Migration20201211() {
        super(VERSION, CHECK_SUM, DESCRIPTION);
    }

    @Override
    public void migrate(Context context) throws Exception {
        createTableSecurityAudit(context);

        alterTableDataAudit(context);
    }

    private void createTableSecurityAudit(Context context) throws DalException {
        logger.entering(this.getClass().getName(), "createTableSecurityAudit ..");
        try {
            DSLContext create = DSL.using(context.getConnection());
            // Конфигурируем
            create.settings()
                    .setStatementType(StatementType.STATIC_STATEMENT);
            // Генерируем таблицу
            String script;
            try (CreateTableColumnStep ctcs = create.createTableIfNotExists(DSL.name(TABLE_SECURITY_AUDIT))) {
                script = ctcs
                        .column(DSL.name(SqlMigrationHelper.Field.ID), SQLDataType.BIGINT.nullable(false))
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
                logger.info(String.format(SqlMigrationHelper.LogTemplate.TABLE_SCRIPT, script));
                create.execute(script);
            }
            {
                // Коментарии
                script = create.commentOnTable(DSL.name(TABLE_SECURITY_AUDIT))
                        .is("security audit")
                        .getSQL();
                logger.info(String.format(SqlMigrationHelper.LogTemplate.COMMENT_SCRIPT, script));
                create.execute(script);
            }
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "createTableSecurityAudit error", ex);
            throw new DalException("createTableSecurityAudit error", ex);
        } finally {
            logger.exiting(this.getClass().getName(), "createTableSecurityAudit done");
        }
    }

    private void alterTableDataAudit(Context context) throws DalException {
        logger.entering(this.getClass().getName(), "alterTableDataAudit ..");
        try {
            DSLContext dsl = DSL.using(context.getConnection());
            // Конфигурируем
            dsl.settings()
                    .setStatementType(StatementType.STATIC_STATEMENT);
            // Генерируем таблицу
            String script;
            {
                // обавляем колонку
                script = dsl.alterTableIfExists(DSL.name(TABLE_DATA_AUDIT))
                        .add(DSL.name(SqlMigrationHelper.Field.STATUS), SQLDataType.VARCHAR(50).nullable(true))
                        .getSQL();
                logger.info(String.format(SqlMigrationHelper.LogTemplate.TABLE_SCRIPT, script));
                dsl.execute(script);
            }
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "alterTableDataAudit error", ex);
            throw new DalException("alterTableDataAudit error", ex);
        } finally {
            logger.exiting(this.getClass().getName(), "alterTableDataAudit done");
        }
    }
}
