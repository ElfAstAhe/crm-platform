package org.hsh.crm.ms.audit.dal.migrations;

import common.dal.migration.SqlMigrationHelper;
import common.dal.migration.base.BaseSqlMigration;
import org.jooq.CreateTableColumnStep;
import org.jooq.DSLContext;
import org.jooq.Query;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;

@SuppressWarnings("unused")
public class Migration20200116 extends BaseSqlMigration {
    private static final String VERSION = "1.1";
    private static final int CHECK_SUM = 1;
    private static final String DESCRIPTION = "user_roles link table";

    private static final String TABLE_USER_ROLES = "user_roles";
    private static final String TABLE_USERS = "users";
    private static final String TABLE_ROLES = "roles";

    private static final String FIELD_USER_ID = "user_id";
    private static final String FIELD_ROLE_ID = "role_id";

    public Migration20200116() {
        super(VERSION, CHECK_SUM, DESCRIPTION);
    }

    @Override
    protected void migrate(DSLContext dslContext) {
        SqlMigrationHelper.Ddl
                .createTable(dslContext, TABLE_USER_ROLES, "user roles", this::buildTableUserRoles);
    }

    private Query buildTableUserRoles(CreateTableColumnStep ctcs) {
        return ctcs.column(DSL.name(FIELD_USER_ID), SQLDataType.BIGINT.nullable(false))
                .column(DSL.name(FIELD_ROLE_ID), SQLDataType.BIGINT.nullable(false))
                .constraints(DSL.constraint(DSL.name(SqlMigrationHelper.Builder.buildFkConstraintName(TABLE_USER_ROLES, "user")))
                                .foreignKey(DSL.name(FIELD_USER_ID))
                                .references(DSL.name(TABLE_USERS), DSL.name(SqlMigrationHelper.Field.ID)),
                        DSL.constraint(DSL.name(SqlMigrationHelper.Builder.buildFkConstraintName(TABLE_USER_ROLES, "role")))
                                .foreignKey(DSL.name(FIELD_ROLE_ID))
                                .references(DSL.name(TABLE_ROLES), DSL.name(SqlMigrationHelper.Field.ID)),
                        DSL.constraint(DSL.name(SqlMigrationHelper.Builder.buildUkConstraintName(TABLE_USER_ROLES)))
                                .unique(DSL.name(FIELD_USER_ID), DSL.name(FIELD_ROLE_ID)));
    }
}
