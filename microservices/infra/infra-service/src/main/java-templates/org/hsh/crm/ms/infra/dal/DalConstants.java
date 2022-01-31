package org.hsh.crm.ms.infra.dal;

public class DalConstants {
    public static final String SCHEMA_NAME = "public";

    public static final String PERSISTENCE_UNIT = "infra.PU";

    public static final String DATA_SOURCE = "${crm-datasource-prefix}crm_infra";

    private DalConstants() {
        // hide
    }
}
