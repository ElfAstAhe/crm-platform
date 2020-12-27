package common.dal.migration;

import common.dal.migration.base.BaseSqlMigration;
import common.exceptions.runtime.MigrationException;
import common.util.ExceptionMessages;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.Configuration;
import org.reflections.Reflections;

import javax.sql.DataSource;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Конечный класс мигратора БД
 *
 * @author elf
 */
public final class DatabaseMigrator implements Migrator {
    private static final String INIT_VERSION = "0.0";
    private static final String INIT_DESCRIPTION = "initialization";
    private static final String REFLECTIONS_PREFIX = "dal.migrations";
    private static final Logger logger = Logger.getLogger(DatabaseMigrator.class.getName());
    private Flyway flyway;
    private final DataSource dataSource;
    private final String[] migrationLocations;

    public static void up(DataSource dataSource, String... migrationLocations) {
        Migrator migrator = new DatabaseMigrator(dataSource, migrationLocations);
        migrator.initialize();
        if (migrator.isInitialized()) {
            migrator.migrateUp();
            return;
        }
        throw new MigrationException("Error migrate up process");
    }

    public static void up(DataSource dataSource) {
        up(dataSource, defaultMigrationLocations());
    }

    public static void down(DataSource dataSource, String... migrationLocations) {
        Migrator migrator = new DatabaseMigrator(dataSource, migrationLocations);
        migrator.initialize();
        if (migrator.isInitialized()) {
            migrator.migrateDown();
            return;
        }
        throw new MigrationException("Error migrate down process");
    }

    public static void down(DataSource dataSource) {
        down(dataSource, defaultMigrationLocations());
    }

    public DatabaseMigrator(DataSource dataSource, String... migrationLocations) {
        this.dataSource = dataSource;
        if( migrationLocations == null || migrationLocations.length <= 0)
            this.migrationLocations = defaultMigrationLocations();
        else
            this.migrationLocations = migrationLocations;
    }

    @Override
    public void initialize() {
        logger.entering(this.getClass().getName(), "initialize ..");
        try {
            flyway = new Flyway(defaultConfiguration());

            logger.exiting(this.getClass().getName(), "initialize done");
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "initialize error", ex);
            throw new MigrationException("initialize error", ex);
        }
    }

    @Override
    public void migrateUp() {
        logger.entering(this.getClass().getName(), "migrateUp ..");
        try {
            flyway.migrate();

            logger.exiting(this.getClass().getName(), "migrateUp done");
        } catch (Exception ex) {
            logger.log(Level.SEVERE, String.format(ExceptionMessages.FMT_MIGRATION_EXCEPTION, ex.getClass().getName(), ex.getMessage()), ex);
            throw new MigrationException("migrateUp error", ex);
        }
    }

    @Override
    public void migrateDown() {
        logger.entering(this.getClass().getName(), "migrateDown ..");
        try {
            flyway.undo();

            logger.exiting(this.getClass().getName(), "migrateDown done");
        } catch (Exception ex) {
            logger.log(Level.SEVERE, String.format(ExceptionMessages.FMT_MIGRATION_EXCEPTION, ex.getClass().getName(), ex.getMessage()), ex);
            throw new MigrationException("migrateDown error", ex);
        }
    }

    @Override
    public void clean() {
        logger.entering(this.getClass().getName(), "clean ..");
        try {
            flyway.clean();

            logger.exiting(this.getClass().getName(), "clean done");
        } catch (Exception ex) {
            logger.log(Level.SEVERE, String.format(ExceptionMessages.FMT_MIGRATION_EXCEPTION, ex.getClass().getName(), ex.getMessage()), ex);
            throw new MigrationException("clean error", ex);
        }
    }

    public boolean isInitialized() {
        return flyway != null;
    }

    private Configuration defaultConfiguration() {
        return Flyway.configure()
                .dataSource(this.dataSource)
                .locations(this.migrationLocations)
                .baselineVersion(INIT_VERSION)
                .baselineDescription(INIT_DESCRIPTION)
                .baselineOnMigrate(true);
    }

    private static String[] defaultMigrationLocations() {
        try {
            Reflections reflections = new Reflections(REFLECTIONS_PREFIX);
            Set<Class<? extends BaseSqlMigration>> migrationClasses = reflections.getSubTypesOf(BaseSqlMigration.class);

            return migrationClasses.stream()
                    .map(c -> c.getPackage().getName().replace('.', '/'))
                    .distinct()
                    .toArray(String[]::new);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, String.format(ExceptionMessages.FMT_MIGRATION_EXCEPTION, ex.getClass().getName(), ex.getMessage()), ex);
            return new String[]{};
        }
    }
}
