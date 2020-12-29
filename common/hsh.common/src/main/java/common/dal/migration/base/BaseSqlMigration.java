package common.dal.migration.base;

import common.exceptions.runtime.MigrationException;
import org.flywaydb.core.api.MigrationVersion;
import org.flywaydb.core.api.migration.Context;
import org.flywaydb.core.api.migration.JavaMigration;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Базовый класс sql миграции
 *
 * @author elf
 */
public abstract class BaseSqlMigration implements JavaMigration {
    private final Logger logger = Logger.getLogger(BaseSqlMigration.class.getName());
    private final String version;
    private final String description;
    private final Integer checkSum;

    public BaseSqlMigration(String version, Integer checkSum, String description) {
        this.version = version;
        this.checkSum = checkSum;
        this.description = description;
    }

    @Override
    public MigrationVersion getVersion() {
        return MigrationVersion.fromVersion(version);
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public Integer getChecksum() {
        return checkSum;
    }

    /**
     * override, if it is undo migration
     * @return undo migration flag
     */
    @Override
    public boolean isUndo() {
        return false;
    }

    @Override
    public boolean canExecuteInTransaction() {
        return false;
    }

    @Override
    public final void migrate(Context context) throws Exception {
        logger.entering(this.getClass().getName(), "migrate ..");
        try {
            migrate(DSL.using(context.getConnection()));

            logger.exiting(this.getClass().getName(), "migrate done");
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "migrate failed", ex);
            throw new MigrationException("migrate failed", ex);
        }
    }

    protected abstract void migrate(DSLContext dslContext);
}
