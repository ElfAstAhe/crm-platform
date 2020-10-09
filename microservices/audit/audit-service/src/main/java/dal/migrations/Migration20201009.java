package dal.migrations;

import common.dal.migration.BaseSqlMigration;
import org.flywaydb.core.api.migration.Context;

public class Migration20201009 extends BaseSqlMigration {
    private static final String VERSION = "1.0";
    private static final int CHECK_SUM = 1;
    private static final String DESCRIPTION = "Initial";

    public Migration20201009() {
        super(VERSION, CHECK_SUM, DESCRIPTION);
    }

    @Override
    public void migrate(Context context) throws Exception {
        createTableSettings(context);

        createTableDataAudit(context);

        createSequenceObjects(context);
    }

    private void createSequenceObjects(Context context) {
        // ..
    }

    private void createTableDataAudit(Context context) {
        // ..
    }

    private void createTableSettings(Context context) {
        // ..
    }
}
