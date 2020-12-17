package common.dal.migration;

import org.jooq.DataType;
import org.jooq.SQLDialect;
import org.jooq.impl.SQLDataType;
import org.jooq.util.mysql.MySQLDataType;

/**
 * helper class for sql migrations
 *
 * @author elf
 */
public class SqlMigrationHelper {
    private SqlMigrationHelper() {
        // hide constructor
    }

    // JOOQ не умеет генерировать MySql longtext тип
    public static DataType<String> serverSpecificDataTypeLongText(SQLDialect sqlDialect) {
        if (SQLDialect.MYSQL.equals(sqlDialect))
            return MySQLDataType.LONGTEXT;
        return SQLDataType.CLOB;
    }

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

    /**
     * Typical table names
     */
    public static class Table {
        public static final String SETTINGS = "settings";

        private Table() {
            // hide constructor
        }
    }

    /**
     * Typical field names
     */
    public static class Field {
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
    public static class LogTemplate {
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
    public static class Naming {
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

    public static class Script {
        public static final String CREATE_PARTITION_TABLE = "create table if not exists %s (constraint %s check(%s = '%s'), constraint %s primary key(%s)) inherits (%s)";

        public static final String CREATE_PARTITION_INSERT_RULE = "create rule %s as on insert to %s where %s do instead insert into %s values(new.*)";

        public static final String DROP_RULE = "drop rule %s on %s";

        public static final String CREATE_INDEX = "create index %s on %s(%s)";

        private Script() {
            // hide constructor
        }
    }
}
