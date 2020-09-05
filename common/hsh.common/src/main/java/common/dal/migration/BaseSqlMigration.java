package common.dal.migration;

import org.flywaydb.core.api.MigrationVersion;
import org.flywaydb.core.api.migration.Context;
import org.flywaydb.core.api.migration.JavaMigration;

/**
 * Базовый класс sql миграции
 *
 * @author elf
 */
public abstract class BaseSqlMigration implements JavaMigration {

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

    @Override
    public boolean isUndo() {
        return false;
    }

    @Override
    public boolean canExecuteInTransaction() {
        return false;
    }

    @Override
    abstract public void migrate(Context context) throws Exception;
}
