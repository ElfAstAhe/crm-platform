package org.hsh.crm.ms.users.dal;

public class DalConstants {
    public static final String SCHEMA_NAME = "public";

    public static final String PERSISTENCE_UNIT = "users.PU";

    public static final String DATA_SOURCE = "${crm-datasource-prefix}crm_users";

    private DalConstants() {
        // hide
    }
}
