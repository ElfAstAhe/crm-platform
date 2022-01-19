package org.hsh.crm.ms.infra.dal.migrations;

import org.hsh.common.dal.migration.SqlMigrationHelper;
import org.hsh.common.dal.migration.base.BaseSqlMigration;
import org.jooq.CreateTableColumnStep;
import org.jooq.DSLContext;
import org.jooq.Query;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;

public class Migration20220105n002 extends BaseSqlMigration {
    private static final String TABLE_NETWORKS = "networks";
    private static final String TABLE_RESOURCES = "resources";
    private static final String TABLE_LOCATIONS = "locations";

    private static final String FIELD_PROVIDER_ID = "provider_id";
    private static final String FIELD_CONTRACT_ID = "contract_id";

    private static final String FIELD_IP_V4 = "ip_v4";
    private static final String FIELD_IP_V6 = "ip_v6";
    private static final String FIELD_NETWORK_ID = "network_id";

    private static final String FIELD_LOCATION_ITEM_ID = "location_item_id";
    private static final String FIELD_SHORT_NAME = "short_name";

    public Migration20220105n002() {
        super("1.1", 1, "Domain objects");
    }

    @Override
    protected void migrate(DSLContext dslContext) {
        SqlMigrationHelper.Ddl.createTable(dslContext, TABLE_NETWORKS, "infra networks", this::createTableNetworks);
        SqlMigrationHelper.Ddl.createTable(dslContext, TABLE_RESOURCES, "infra resources", this::createTableResources);
    }

    private Query createTableNetworks(CreateTableColumnStep ctcs) {
        return ctcs.column(DSL.name(SqlMigrationHelper.Field.ID), SQLDataType.BIGINT.nullable(false))
                .column(DSL.name(SqlMigrationHelper.Field.CODE), SQLDataType.VARCHAR(50).nullable(false))
                .column(DSL.name(SqlMigrationHelper.Field.NAME), SQLDataType.VARCHAR(100).nullable(true))
                .column(DSL.name(FIELD_PROVIDER_ID), SQLDataType.BIGINT.nullable(true))
                .column(DSL.name(FIELD_CONTRACT_ID), SQLDataType.BIGINT.nullable(true))
                .constraints(DSL.constraint(SqlMigrationHelper.Builder.buildPkConstraintName(TABLE_NETWORKS)).primaryKey(DSL.name(SqlMigrationHelper.Field.ID)),
                        DSL.constraint(SqlMigrationHelper.Builder.buildUkConstraintName(TABLE_NETWORKS)).unique(DSL.name(SqlMigrationHelper.Field.CODE)));
    }

    private Query createTableResources(CreateTableColumnStep ctcs) {
        return ctcs.column(DSL.name(SqlMigrationHelper.Field.ID), SQLDataType.BIGINT.nullable(false))
                .column(DSL.name(SqlMigrationHelper.Field.CODE), SQLDataType.VARCHAR(50).nullable(false))
                .column(DSL.name(SqlMigrationHelper.Field.NAME), SQLDataType.VARCHAR(512).nullable(true))
                .column(DSL.name(SqlMigrationHelper.Field.PORT), SQLDataType.INTEGER.nullable(true))
                .column(DSL.name(FIELD_IP_V4), SQLDataType.VARCHAR(50).nullable(true))
                .column(DSL.name(FIELD_IP_V6), SQLDataType.VARCHAR(100).nullable(true))
                .column(DSL.name(SqlMigrationHelper.Field.URL), SQLDataType.VARCHAR(1024).nullable(true))
                .column(DSL.name(SqlMigrationHelper.Field.DESCRIPTION), SQLDataType.VARCHAR(512).nullable(true))
                .column(DSL.name(FIELD_NETWORK_ID), SQLDataType.BIGINT.nullable(true))
                .constraints(DSL.constraint(SqlMigrationHelper.Builder.buildPkConstraintName(TABLE_RESOURCES)).primaryKey(DSL.name(SqlMigrationHelper.Field.ID)),
                        DSL.constraint(SqlMigrationHelper.Builder.buildUkConstraintName(TABLE_RESOURCES)).unique(DSL.name(SqlMigrationHelper.Field.CODE), DSL.name(FIELD_NETWORK_ID)),
                        DSL.constraint(SqlMigrationHelper.Builder.buildFkConstraintName(TABLE_RESOURCES, "network")).foreignKey(DSL.name(FIELD_NETWORK_ID)).references(DSL.name(TABLE_NETWORKS), DSL.name(SqlMigrationHelper.Field.ID)));
    }

    private Query createTableLocations(CreateTableColumnStep ctcs) {
        return ctcs.column(DSL.name(SqlMigrationHelper.Field.ID), SQLDataType.BIGINT.nullable(false))
                .column(DSL.name(FIELD_LOCATION_ITEM_ID), SQLDataType.BIGINT.nullable(true))
                .column(DSL.name(FIELD_SHORT_NAME), SQLDataType.VARCHAR(50).nullable(true))
                .column(DSL.name(FIELD_NETWORK_ID), SQLDataType.BIGINT.nullable(true))
                .constraint(DSL.constraint(DSL.name(SqlMigrationHelper.Builder.buildPkConstraintName(TABLE_LOCATIONS))).primaryKey(DSL.name(SqlMigrationHelper.Field.ID)));
    }
}
