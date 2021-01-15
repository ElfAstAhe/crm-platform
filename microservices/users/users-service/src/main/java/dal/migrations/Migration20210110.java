package dal.migrations;

import common.dal.migration.SqlMigrationHelper;
import common.dal.migration.base.BaseSqlMigration;
import org.jooq.CreateTableColumnStep;
import org.jooq.DSLContext;
import org.jooq.Query;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;

@SuppressWarnings("unused")
public class Migration20210110 extends BaseSqlMigration {
    private static final String VERSION = "1.0";
    private static final int CHECK_SUM = 0;
    private static final String DESCRIPTION = "Initial";

    private static final String TABLE_USERS = "users";
    private static final String TABLE_ROLES = "roles";

    public Migration20210110() {
        super(VERSION, CHECK_SUM, DESCRIPTION);
    }

    @Override
    protected void migrate(DSLContext dslContext) {
        SqlMigrationHelper.createDefaultSequenceObjects(dslContext);

        SqlMigrationHelper.createDefaultTableSettings(dslContext);

        SqlMigrationHelper.Ddl
                .createTable(dslContext, TABLE_USERS, "system users", this::buildTableUsers);

        SqlMigrationHelper.Ddl
                .createTable(dslContext, TABLE_ROLES, "system roles", this::buildTableRoles);
    }

    private Query buildTableUsers(CreateTableColumnStep ctcs) {
        return ctcs.column(DSL.name(SqlMigrationHelper.Field.ID), SQLDataType.BIGINT.nullable(false))
                .column(DSL.name("username"), SQLDataType.VARCHAR(100).nullable(false))
                .column(DSL.name("password"), SQLDataType.VARCHAR(100).nullable(true))
                .column(DSL.name("password_encrypted"), SQLDataType.VARCHAR(200).nullable(true))
                .column(DSL.name("private_key"), SQLDataType.VARCHAR(4096).nullable(true))
                .column(DSL.name("public_key"), SQLDataType.VARCHAR(4096).nullable(true))
                .column(DSL.name("person"), SQLDataType.VARCHAR(200).nullable(true))
                .column(DSL.name("e_mail"), SQLDataType.VARCHAR(1024).nullable(true))
                .column(DSL.name("cell_phone"), SQLDataType.VARCHAR(50).nullable(true))
                .column(DSL.name(SqlMigrationHelper.Field.STATE), SQLDataType.VARCHAR(50).nullable(false).defaultValue("DRAFT"))
                .constraints(DSL.constraint(DSL.name(SqlMigrationHelper.Builder.buildPkConstraintName(TABLE_USERS)))
                                .primaryKey(DSL.name(SqlMigrationHelper.Field.ID)),
                        DSL.constraint(DSL.name(SqlMigrationHelper.Builder.buildUkConstraintName(TABLE_USERS)))
                                .unique(DSL.name("username")));
    }

    private Query buildTableRoles(CreateTableColumnStep ctcs) {
        return ctcs.column(DSL.name(SqlMigrationHelper.Field.ID), SQLDataType.BIGINT.nullable(false))
                .column(DSL.name(SqlMigrationHelper.Field.CODE), SQLDataType.VARCHAR(50).nullable(false))
                .column(DSL.name(SqlMigrationHelper.Field.NAME), SQLDataType.VARCHAR(100).nullable(true))
                .column(DSL.name(SqlMigrationHelper.Field.DESCRIPTION), SQLDataType.VARCHAR(512).nullable(true))
                .constraints(DSL.constraint(DSL.name(SqlMigrationHelper.Builder.buildPkConstraintName(TABLE_ROLES)))
                                .primaryKey(DSL.name(SqlMigrationHelper.Field.ID)),
                        DSL.constraint(DSL.name(SqlMigrationHelper.Builder.buildUkConstraintName(TABLE_ROLES)))
                                .unique(DSL.name(SqlMigrationHelper.Field.CODE)));
    }
}
