package org.hsh.crm.ms.audit.dal;

public class DalConstants {
    public static final String SCHEMA_NAME = "public";

    public static final String PERSISTENCE_UNIT = "audit.PU";

    public static final String DATA_SOURCE = "${crm-datasource-prefix}crm_audit";

    private DalConstants() {
        // hide
    }
}
