package common.dal.migration.base;

import org.flywaydb.core.api.migration.Context;
import org.jooq.DSLContext;

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
    protected void migrate(DSLContext context) {
        // реализуем когда-нибудь в будующем ... :-)
    }
}
