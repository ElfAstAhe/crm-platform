package common.dal.migration;

/**
 * Интерфейс мигратор
 *
 * @author elf
 */
public interface Migrator {
    /**
     * Инициализация
     */
    void initialize();

    /**
     * Миграция вверх
     */
    void migrateUp();

    /**
     * Миграция вниз
     */
    void migrateDown();

    /**
     * Очистка
     */
    void clean();

    boolean isInitialized();
}
