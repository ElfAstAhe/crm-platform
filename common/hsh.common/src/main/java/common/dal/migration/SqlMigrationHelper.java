package common.dal.migration;

import org.jooq.*;
import org.jooq.conf.StatementType;
import org.jooq.impl.DSL;
import org.jooq.impl.DefaultDataType;
import org.jooq.impl.SQLDataType;
import org.jooq.tools.StringUtils;

import java.sql.Date;
import java.util.function.Function;
import java.util.logging.Logger;

/**
 * helper class for sql migrations
 *
 * @author elf
 */
@SuppressWarnings("unused")
public final class SqlMigrationHelper {
    private static final Logger logger = Logger.getLogger(SqlMigrationHelper.class.getName());
    private static final String ERROR_CONTEXT_NOT_ASSIGNED = "context not assigned";
    private static final String ERROR_TABLE_NAME_IS_EMPTY = "tableName is empty";
    private static final String ERROR_BUILDER_NOT_ASSIGNED = "builder not assigned";

    public static final DataType<String> MYSQL_TINYTEXT = new DefaultDataType<>(SQLDialect.MYSQL, SQLDataType.CLOB, "tinytext", "char");
    public static final DataType<String> MYSQL_MEDIUMTEXT = new DefaultDataType<>(SQLDialect.MYSQL, SQLDataType.CLOB, "mediumtext", "char");
    public static final DataType<String> MYSQL_LONGTEXT = new DefaultDataType<>(SQLDialect.MYSQL, SQLDataType.CLOB, "longtext", "char");
    public static final DataType<String> MYSQL_ENUM = new DefaultDataType<>(SQLDialect.MYSQL, SQLDataType.VARCHAR, "enum", "char");
    public static final DataType<String> MYSQL_SET = new DefaultDataType<>(SQLDialect.MYSQL, SQLDataType.VARCHAR, "set", "char");
    public static final DataType<byte[]> MYSQL_TINYBLOB = new DefaultDataType<>(SQLDialect.MYSQL, SQLDataType.BLOB, "tinyblob", "binary");
    public static final DataType<byte[]> MYSQL_MEDIUMBLOB = new DefaultDataType<>(SQLDialect.MYSQL, SQLDataType.BLOB, "mediumblob", "binary");
    public static final DataType<byte[]> MYSQL_LONGBLOB = new DefaultDataType<>(SQLDialect.MYSQL, SQLDataType.BLOB, "longblob", "binary");
    public static final DataType<Date>   MYSQL_YEAR = new DefaultDataType<>(SQLDialect.MYSQL, SQLDataType.DATE, "year", "date");
    public static final DataType<JSON>   MYSQL_JSON = new DefaultDataType<>(SQLDialect.MYSQL, SQLDataType.JSON, "json");

    private SqlMigrationHelper() {
        // hide constructor
    }

    // JOOQ не умеет генерировать MySql longtext тип
    public static DataType<String> serverSpecificDataTypeLongText(SQLDialect sqlDialect) {
        if (SQLDialect.MYSQL.equals(sqlDialect))
            return MYSQL_LONGTEXT;
        return SQLDataType.CLOB;
    }

    public static void createDefaultSequenceObjects(DSLContext dslContext) {
        Ddl.createSequence(dslContext, Sequence.OBJECTS,
                csfs -> csfs.incrementBy(DSL.val(1L))
                        .minvalue(DSL.val(1L))
                        .startWith(DSL.val(1L))
                        .cache(DSL.val(1L))
                        .noCycle());
    }

    public static void createDefaultTableSettings(DSLContext dslContext) {
        Ddl.createTable(dslContext, Table.SETTINGS, Table.SETTINGS_DESCRIPTION,
                ctcs -> ctcs.column(DSL.name(SqlMigrationHelper.Field.ID), SQLDataType.BIGINT.nullable(false))
                        .column(DSL.name(SqlMigrationHelper.Field.VERSION), SQLDataType.BIGINT.nullable(false))
                        .column(DSL.name(SqlMigrationHelper.Field.CODE), SQLDataType.VARCHAR(50).nullable(false))
                        .column(DSL.name(SqlMigrationHelper.Field.NAME), SQLDataType.VARCHAR(100).nullable(true))
                        .column(DSL.name(SqlMigrationHelper.Field.VALUE), SQLDataType.VARCHAR(1024).nullable(true))
                        .constraints(DSL.constraint(DSL.name(SqlMigrationHelper.Builder.buildPkConstraintName(Table.SETTINGS)))
                                        .primaryKey(DSL.name(SqlMigrationHelper.Field.ID)),
                                DSL.constraint(DSL.name(SqlMigrationHelper.Builder.buildUkConstraintName(Table.SETTINGS)))
                                        .unique(DSL.name(SqlMigrationHelper.Field.CODE))));
    }

    public static final class Ddl {
        private Ddl() {
            // hide
        }

        public static void createTable(DSLContext context,
                                       String tableName,
                                       String tableDescription,
                                       Function<CreateTableColumnStep, Query> columnsBuilder) {
            logger.entering(SqlMigrationHelper.class.getName(), String.format("createTable [%s] ..", tableName));
            if (context == null)
                throw new IllegalArgumentException(ERROR_CONTEXT_NOT_ASSIGNED);
            if (StringUtils.isBlank(tableName))
                throw new IllegalArgumentException(ERROR_TABLE_NAME_IS_EMPTY);
            if (columnsBuilder == null)
                throw new IllegalArgumentException(ERROR_BUILDER_NOT_ASSIGNED);
            // Настраиваем
            context.settings()
                    .setStatementType(StatementType.STATIC_STATEMENT);
            // Таблица
            try (CreateTableColumnStep ctcs = context.createTableIfNotExists(DSL.name(tableName))) {
                String script = columnsBuilder.apply(ctcs).getSQL();
                logger.info(String.format(LogTemplate.TABLE_SCRIPT, script));
                context.execute(script);
            }
            // Коменты
            if (!StringUtils.isBlank(tableDescription)) {
                String script = context.commentOnTable(DSL.name(tableName))
                        .is(tableDescription)
                        .getSQL();
                logger.info(String.format(LogTemplate.COMMENT_SCRIPT, script));
                context.execute(script);
            }
            logger.exiting(SqlMigrationHelper.class.getName(), String.format("createTable [%s] done", tableName));
        }

        public static void createTable(DSLContext context,
                                       String tableName,
                                       Function<CreateTableColumnStep, Query> columnsBuilder) {
            createTable(context, tableName, null, columnsBuilder);
        }

        public static void createSequence(DSLContext context, String sequenceName, Function<CreateSequenceFlagsStep, Query> sequenceBuilder) {
            logger.entering(SqlMigrationHelper.class.getName(), String.format("createSequence [%s] ..", sequenceName));
            if (context == null)
                throw new IllegalArgumentException(ERROR_CONTEXT_NOT_ASSIGNED);
            if (StringUtils.isBlank(sequenceName))
                throw new IllegalArgumentException("sequenceName is empty");
            if (sequenceBuilder == null)
                throw new IllegalArgumentException(ERROR_BUILDER_NOT_ASSIGNED);
            // стройки
            context.settings()
                    .setStatementType(StatementType.STATIC_STATEMENT);
            // сиквенс
            try (CreateSequenceFlagsStep csfs = context.createSequenceIfNotExists(DSL.sequence(DSL.name(sequenceName), SQLDataType.BIGINT))) {
                String script = sequenceBuilder.apply(csfs).getSQL();
                logger.info(String.format(LogTemplate.SEQUENCE_SCRIPT, script));
                context.execute(script);
            }
            logger.exiting(SqlMigrationHelper.class.getName(), String.format("createSequenceObjects [%s] done", sequenceName));
        }

        public static void alterTable(DSLContext context, String tableName, Function<AlterTableStep, Query> columnsBuilder) {
            logger.entering(SqlMigrationHelper.class.getName(), String.format("alterTable [%s] ..", tableName));
            if (context == null)
                throw new IllegalArgumentException(ERROR_CONTEXT_NOT_ASSIGNED);
            if (StringUtils.isBlank(tableName))
                throw new IllegalArgumentException(ERROR_TABLE_NAME_IS_EMPTY);
            if (columnsBuilder == null)
                throw new IllegalArgumentException(ERROR_BUILDER_NOT_ASSIGNED);
            // Конфигурируем
            context.settings()
                    .setStatementType(StatementType.STATIC_STATEMENT);
            // изменяем таблицу
            String script = columnsBuilder.apply(context.alterTableIfExists(DSL.name(tableName)))
                    .getSQL();
            logger.info(String.format(LogTemplate.TABLE_SCRIPT, script));
            context.execute(script);

            logger.exiting(SqlMigrationHelper.class.getName(), String.format("alterTable [%s] done", tableName));
        }
    }

    public static final class Builder {
        public static String buildPkConstraintName(String tableName) {
            return String.format(Naming.PK_CONSTRAINT, tableName);
        }

        public static String buildFkConstraintName(String tableName, String suffix) {
            return String.format(Naming.FK_CONSTRAINT, tableName, suffix);
        }

        public static String buildUkConstraintName(String tableName) {
            return String.format(Naming.UK_BK_CONSTRAINT, tableName);
        }

        public static String buildUkConstraintName(String tableName, String suffix) {
            return String.format(Naming.UK_CONSTRAINT, tableName, suffix);
        }

        public static String buildChSimpleConstraintName(String tableName, String fieldName) {
            return String.format(Naming.CH_CONSTRAINT_SIMPLE, tableName, fieldName);
        }

        public static String buildChConstraintName(String tableName, String fieldName, String suffixName) {
            return String.format(Naming.CH_CONSTRAINT, tableName, fieldName, suffixName);
        }

        public static String buildIndexName(String tableName, String suffix) {
            return String.format(Naming.INDEX, tableName, suffix);
        }

        public static String buildPartitionTableName(String tableName, String suffix) {
            return String.format(Naming.PARTITION_TABLE, tableName, suffix);
        }

        public static String buildPostgresRuleName(String tableName, String suffix1, String suffix2) {
            return String.format(Naming.POSTGRES_RULE, tableName, suffix1, suffix2);
        }

        public static String buildPostgresPartitionTableScript(String tableName, String partitionTableName, String pkFieldName, String chFieldName, String partitionValue) {
            String chConstraintName = buildChSimpleConstraintName(partitionTableName, chFieldName);
            String pkConstraintName = buildPkConstraintName(partitionTableName);
            return String.format(Script.CREATE_PARTITION_TABLE, partitionTableName, chConstraintName, chFieldName, partitionValue, pkConstraintName, pkFieldName, tableName);
        }

        public static String buildPostgresCreateInsertRuleScript(String tableName, String partitionTableName, String ruleName, String condition) {
            return String.format(Script.CREATE_PARTITION_INSERT_RULE, ruleName, tableName, condition, partitionTableName);
        }

        public static String buildPostgresDropRule(String tableName, String ruleName) {
            return String.format(Script.DROP_RULE, ruleName, tableName);
        }
    }

    /**
     * Typical sequence names
     */
    public static final class Sequence {
        public static final String OBJECTS = "seq_objects";

        private Sequence() {
            // hide
        }
    }

    /**
     * Typical table names
     */
    public static final class Table {
        public static final String SETTINGS = "settings";

        public static final String SETTINGS_DESCRIPTION = "microservice changeable settings";

        private Table() {
            // hide constructor
        }
    }

    /**
     * Typical field names
     */
    public static final class Field {
        public static final String ID = "id";
        public static final String TYPE = "type";
        public static final String KIND = "kind";
        public static final String CODE = "code";
        public static final String NAME = "name";
        public static final String VERSION = "version";
        public static final String DESCRIPTION = "description";
        public static final String COMMENTS = "comments";
        public static final String DELETE_FLAG = "delete_flag";
        public static final String VALUE = "value";
        public static final String STATUS = "status";
        public static final String STATE = "state";
        public static final String EVENT = "event";
        public static final String REQUEST_ID = "request_id";
        public static final String CURRENCY = "currency";
        public static final String SOURCE = "source";
        public static final String USER_LOGIN = "user_login";

        public static final String QUOTE_DATE = "quote_date";
        public static final String CRYPTO_CURRENCY = "crypto_currency";
        public static final String EXCHANGE = "exchange";
        public static final String CRYPTO_EXCHANGE = "crypto_exchange";
        public static final String CURRENCY_VALUE = "currency_value";
        public static final String CRYPTO_CURRENCY_VALUE = "crypto_currency_value";

        private Field() {
            // hide constructor
        }
    }

    /**
     * Log message templates
     */
    public static final class LogTemplate {
        public static final String TABLE_SCRIPT = "table script: [%s]";
        public static final String COMMENT_SCRIPT = "comment script: [%s]";
        public static final String INDEX_SCRIPT = "index script: [%s]";
        public static final String SEQUENCE_SCRIPT = "sequence script: [%s]";
        public static final String INSERT_SCRIPT = "insert script: [%s]";
        public static final String RULE_SCRIPT = "rule script: [%s]";

        private LogTemplate() {
            // hide constructor
        }
    }

    /**
     * Naming templates
     */
    public static final class Naming {
        public static final String INDEX = "idx_%s_%s";
        public static final String PK_CONSTRAINT = "%s_pk";
        public static final String FK_CONSTRAINT = "%s_fk_%s";
        public static final String UK_CONSTRAINT = "%s_uk_%s";
        public static final String UK_BK_CONSTRAINT = "%s_uk";
        public static final String CH_CONSTRAINT = "%s_ch_%s_%s";
        public static final String CH_CONSTRAINT_SIMPLE = "%s_ch_%s";
        public static final String PARTITION_TABLE = "%s_%s";
        public static final String POSTGRES_RULE = "%s_%s_%s";

        private Naming() {
            // hide constructor
        }
    }

    public static final class Script {
        public static final String CREATE_PARTITION_TABLE = "create table if not exists %s (constraint %s check(%s = '%s'), constraint %s primary key(%s)) inherits (%s)";

        public static final String CREATE_PARTITION_INSERT_RULE = "create rule %s as on insert to %s where %s do instead insert into %s values(new.*)";

        public static final String DROP_RULE = "drop rule %s on %s";

        public static final String CREATE_INDEX = "create index %s on %s(%s)";

        private Script() {
            // hide constructor
        }
    }
}
