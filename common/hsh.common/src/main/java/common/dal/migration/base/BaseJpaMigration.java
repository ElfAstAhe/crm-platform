package common.dal.migration.base;

import org.flywaydb.core.api.migration.Context;

/**
 * Базовый класс миграции на основе JPA entity
 *
 * @author elf
 */
public class BaseJpaMigration extends BaseSqlMigration {

    private String[] entityLocations;

    public BaseJpaMigration(String version, Integer checkSum, String description, String... entityLocations) {
        super(version,checkSum,description);
        this.entityLocations = entityLocations;
    }

    @Override
    public void migrate(Context context) throws Exception {
        // реализуем когда-нибудь в будующем ... :-)
    }
}
