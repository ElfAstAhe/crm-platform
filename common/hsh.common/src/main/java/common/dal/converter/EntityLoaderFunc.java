package common.dal.converter;

import common.dal.entity.Identity;

/**
 * Функциональный интерфейс загрузки entity
 *
 * @author elf
 * @param <Entity> тип entity
 */
public interface EntityLoaderFunc<Entity extends Identity> {
    Entity find(Long id);
}
