package common.dal.migration;

import common.exceptions.DalException;

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
    void migrateUp() throws DalException;

    /**
     * Миграция вниз
     */
    void migrateDown() throws DalException;

    /**
     * Очистка
     */
    void clean() throws DalException;

    boolean isInitialized();
}
