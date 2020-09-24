package common.dal.converter;

import common.dal.entity.IdEntity;

/**
 * Функциональный интерфейс загрузки entity
 *
 * @author elf
 * @param <Entity> тип entity
 */
public interface EntityLoaderFunc<Entity extends IdEntity> {
    Entity find(Long id);
}
