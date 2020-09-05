package common.dal.migration;

import common.util.ExceptionMessages;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.Configuration;

import javax.sql.DataSource;
import java.util.logging.Logger;

/**
 * Конечный класс мигратора БД
 *
 * @author elf
 */
public final class DatabaseMigrator implements Migrator {

    private final Logger logger;
    private Flyway flyway;
    private final DataSource dataSource;
    private final String[] migrationLocations;

    public static void up(DataSource dataSource, String... migrationLocations) {
        Migrator migrator = new DatabaseMigrator(dataSource, migrationLocations);
        migrator.initialize();
        if (migrator.isInitialized())
            migrator.migrateUp();
    }

    public static void down(DataSource dataSource, String... migrationLocations) {
        Migrator migrator = new DatabaseMigrator(dataSource, migrationLocations);
        migrator.initialize();
        if (migrator.isInitialized())
            migrator.migrateDown();
    }

    public DatabaseMigrator(DataSource dataSource, String... migrationLocations) {
        this.logger = Logger.getLogger(this.getClass().getName());
        this.dataSource = dataSource;
        this.migrationLocations = migrationLocations;
    }

    @Override
    public void initialize() {
        flyway = new Flyway(defaultConfiguration());
    }

    @Override
    public void migrateUp() {
        logger.entering(this.getClass().getName(), "migrateUp");
        try {
            flyway.migrate();
        } catch (Exception ex) {
            logger.severe(String.format(ExceptionMessages.FMT_MIGRATION_EXCEPTION, ex.getClass().getName(), ex.getMessage()));
        } finally {
            logger.exiting(this.getClass().getName(), "migrateUp");
        }
    }

    @Override
    public void migrateDown() {
        logger.entering(this.getClass().getName(), "migrateDown");
        try {
            flyway.undo();
        } catch (Exception ex) {
            logger.severe(String.format(ExceptionMessages.FMT_MIGRATION_EXCEPTION, ex.getClass().getName(), ex.getMessage()));
        } finally {
            logger.exiting(this.getClass().getName(), "migrateDown");
        }
    }

    @Override
    public void clean() {
        logger.entering(this.getClass().getName(), "migrateDown");
        try {
            flyway.clean();
        } catch (Exception ex) {
            logger.severe(String.format(ExceptionMessages.FMT_MIGRATION_EXCEPTION, ex.getClass().getName(), ex.getMessage()));
        } finally {
            logger.exiting(this.getClass().getName(), "migrateDown");
        }
    }

    public boolean isInitialized() {
        return flyway != null;
    }

    private Configuration defaultConfiguration() {
        return Flyway.configure()
                .dataSource(this.dataSource)
                .locations(this.migrationLocations)
                .baselineVersion("0.0")
                .baselineDescription("initialization")
                .baselineOnMigrate(true);
    }
}
